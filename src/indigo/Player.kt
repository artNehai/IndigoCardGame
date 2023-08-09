package indigo

import kotlin.system.exitProcess

class Player(
    cardDeck: Deck
) : Contender(cardDeck) {

    fun makeMove() {
        printHand()

        var cardPlayed: Card
        while (true) {
            println("Choose a card to play (1-${hand.size}):")

            val input = readln().lowercase()
            if (input == "exit") {
                println("Game over")
                exitProcess(0)
            }

            try {
                val cardIndex = input.toInt() - 1
                cardPlayed = hand[cardIndex]
                break
            } catch (e: NumberFormatException) {
                continue
            } catch (e: IndexOutOfBoundsException) {
                continue
            }
        }

        playCard(cardPlayed)
    }
}