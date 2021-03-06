package com.github.aglassman.cardengine


class Player(
    val name: String
) {
  private var _hand: MutableList<Card> = mutableListOf()

  fun hand() = _hand.toList()

  fun recieveCards(cards: List<Card>) {
    println("$name recieved ${cards.joinToString { "${it.toUnicodeString()}" }}")
    _hand.addAll(cards)
  }

  fun requestCard(cardIndex: Int) = requestCards(listOf(cardIndex)).first()

  fun requestCards(requestedCards: List<Int>): List<Card> {

    requestedCards
        .distinct()
        .forEach {
          if(it > _hand.size) throw GameException("Requested card at index $it, but hand only has ${_hand.size} cards.")
        }

    val foundCards = requestedCards
        .distinct()
        .map {
          _hand[it]
        }

    _hand.removeAll(foundCards)

    return foundCards

  }

  fun isPlayer(player: Player?) = this.name == player?.name
  override fun toString(): String {
    return name
  }


}