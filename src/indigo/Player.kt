package indigo

import kotlin.system.exitProcess

class Player(
    private val cardDeck: Deck
) : Contender() {

    fun makeMove() {
        cardDeck.printHand()

        var cardPlayed: Int
        while (true) {
            println("Choose a card to play (1-${cardDeck.playersHandSize}):")

            val input = readln().lowercase()
            if (input == "exit") {
                println("Game over")
                exitProcess(0)
            }

            try {
                cardPlayed = input.toInt()
            } catch (e: NumberFormatException) {
                continue
            }

            if (cardPlayed in 1..cardDeck.playersHandSize) {
                break
            }
        }
        println()

        cardDeck.playCard(cardPlayed - 1, USER_ID)
    }
}