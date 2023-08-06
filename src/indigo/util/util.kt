package indigo.util

import indigo.Deck
import kotlin.system.exitProcess

const val USER_ID = 1
const val COMPUTER_ID = 2
const val INITIAL_CARDS_ON_TABLE = 4


fun play(cardDeck: Deck, isUserFirst: Boolean) {

    print("Initial cards on the table: ")
    println(
        cardDeck.cardsOnTable.joinToString(" ")
    )
    println()

    var isUsersTurn = isUserFirst
    while (cardDeck.isNotEmpty) {
        println(
            "${cardDeck.cardsOnTable.size} cards on the table, and the top card is ${cardDeck.cardsOnTable.last()}"
        )
        if (isUsersTurn) {
            playerPlays(cardDeck)
            isUsersTurn = false
        } else {
            computerPlays(cardDeck)
            isUsersTurn = true
        }
    }
    println("Game Over")
    return
}

fun playerPlays(cardDeck: Deck) {

    cardDeck.printHand()

    var cardPlayed: Int
    while (true) {
        println("Choose a card to play (1-${cardDeck.playersHand.size}):")

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

        if (cardPlayed in 1..cardDeck.playersHand.size) {
            break
        }
    }
    println()

    cardDeck.playCard(cardPlayed - 1, USER_ID)
}

fun computerPlays(cardDeck: Deck) {
    println(
        "Computer plays " + cardDeck.playCard(0, COMPUTER_ID)
    )
    println()
}
