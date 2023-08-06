package indigo

import indigo.util.COMPUTER_ID
import indigo.util.INITIAL_CARDS_ON_TABLE
import indigo.util.USER_ID

const val CARDS_NUM = 52
const val CARDS_IN_HAND = 6

class Deck {

    private val initialCardDeck: List<String> =
        Ranks.values().flatMap { rank ->
            Suits.values().map { suit ->
                "$rank$suit"
            }
        }

    private var cardDeck: List<String> = initialCardDeck.shuffled()

    var cardsOnTable: List<String> = getCards(INITIAL_CARDS_ON_TABLE).toMutableList()
        private set

    var playersHand: List<String> = getCards(CARDS_IN_HAND).toMutableList()
        private set

    private val computersHand: MutableList<String> = getCards(CARDS_IN_HAND).toMutableList()

    val isNotEmpty = cardDeck.isNotEmpty()

    fun reset() {
        cardDeck = initialCardDeck
        println("Card deck is reset.")
    }

    fun shuffle() {
        cardDeck = cardDeck.shuffled()
        println("Card deck is shuffled.")
    }

    private fun getCards(quantity: Int): List<String> {
        val cardsList = cardDeck.take(quantity)
        cardDeck = cardDeck.drop(quantity)

        return cardsList
    }

    fun getCards() {

        println("Number of cards:")
        val quantity: Int

        try {
            quantity = readln().toInt()
            if (quantity !in 1..CARDS_NUM) {
                throw NumberFormatException()
            }
        } catch (e: NumberFormatException) {
            println("Invalid number of cards.")
            return
        }

        if (quantity > cardDeck.size) {
            println("The remaining cards are insufficient to meet the request.")
            return
        }

        val cardsList = cardDeck.take(quantity)
        println(
            cardsList.joinToString(" ")
        )
        cardDeck = cardDeck.drop(quantity)
    }

    fun printHand() {
        print("Cards in hand: ")
        for (i in playersHand.indices) {
            print("${i + 1})" + playersHand[i] + " ")
        }
        println()
    }

    fun playCard(index: Int, contenderId: Int): String {
        val hand =
            when (contenderId) {
                USER_ID -> playersHand.toMutableList()
                COMPUTER_ID -> computersHand
                else -> throw IllegalArgumentException("There are only two possible contenders: user, computer")
            }

        val card = hand[index]
        cardsOnTable += card
        hand.removeAt(index)
        playersHand = hand

        return card
    }
}