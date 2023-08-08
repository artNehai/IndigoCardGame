package indigo

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

    startGame(cardDeck, isUserFirst)
}

