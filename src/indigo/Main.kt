package indigo

const val CARDS_NUM = 52

enum class Ranks(private val rank: String) {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    override fun toString() = rank
}

enum class Suits(private val suit: String) {
    DIAMONDS("♦"),
    HEARTS("♥"),
    SPADES("♠"),
    CLUBS("♣");

    override fun toString() = suit
}

val initialCardDeck: List<String> =
    Ranks.values().flatMap { rank ->
        Suits.values().map { suit ->
            "$rank$suit"
        }
    }



class Deck {

    private var cardDeck: List<String> = initialCardDeck

    fun reset() {
        cardDeck = initialCardDeck
        println("Card deck is reset.")
    }

    fun shuffle() {
        cardDeck = cardDeck.shuffled()
        println("Card deck is shuffled.")
    }

    fun getCard(): Unit {

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

        println(
            cardDeck.take(quantity).joinToString(" ")
        )
        cardDeck = cardDeck.drop(quantity)
    }
}



fun main() {

    val cardDeck = Deck()

    while (true) {
        println("Choose an action (reset, shuffle, get, exit):")
        when (readln().lowercase()) {
            "reset" -> cardDeck.reset()
            "shuffle" -> cardDeck.shuffle()
            "get" -> cardDeck.getCard()
            "exit" -> {
                println("Bye")
                break
            }

            else -> println("Wrong action.")
        }
    }
}