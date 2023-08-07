package indigo

const val INITIAL_CARDS_ON_TABLE = 4
const val CARDS_IN_HAND = 6


class Deck {

    private val cardDeck: MutableList<String> =
        Ranks.values().flatMap { rank ->
            Suits.values().map { suit ->
                "$rank$suit"
            }
        }.shuffled().toMutableList()

    var cardsOnTable: List<String> = pullCards(INITIAL_CARDS_ON_TABLE)
        private set

    private val playersHand: MutableList<String> = pullCards(CARDS_IN_HAND).toMutableList()
    private val computersHand: MutableList<String> = pullCards(CARDS_IN_HAND).toMutableList()

    val handsIsNotEmpty: Boolean
        get() = playersHand.isNotEmpty() || computersHand.isNotEmpty()

    val playersHandSize: Int
        get() = playersHand.size


    private fun pullCards(quantity: Int): List<String> {
        val cardsList = cardDeck.take(quantity)
        cardDeck.removeAll(cardsList)

        return cardsList
    }

    fun printHand() {
        print("Cards in hand: ")
        for (i in playersHand.indices) {
            print("${i + 1})" + playersHand[i] + " ")
        }
        println()
    }

    fun playCard(index: Int, contenderId: Int): String {
        val hand: MutableList<String> =
            when (contenderId) {
                USER_ID -> playersHand
                COMPUTER_ID -> computersHand
                else -> throw IllegalArgumentException("There are only two possible contenders: user, computer")
            }

        val card = hand[index]
        cardsOnTable += card
        hand.removeAt(index)

        if (hand.isEmpty()) {
            hand.addAll(
                pullCards(CARDS_IN_HAND)
            )
        }

        return card
    }
}