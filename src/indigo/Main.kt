package indigo

import indigo.util.*


fun main() {

    println("Indigo Card Game")

    val cardDeck = Deck()

    val isUserFirst: Boolean
    while (true) {
        println("Play first?")
        when (readln().lowercase()) {
            "yes" -> {
                isUserFirst = true
                break
            }

            "no" -> {
                isUserFirst = false
                break
            }
        }
    }

    play(cardDeck, isUserFirst)

    while (true) {
        println("Choose an action (reset, shuffle, get, exit):")
        when (readln().lowercase()) {
            "reset" -> cardDeck.reset()
            "shuffle" -> cardDeck.shuffle()
            "get" -> cardDeck.getCards()
            "exit" -> {
                println("Bye")
                break
            }

            else -> println("Wrong action.")
        }
    }
}

