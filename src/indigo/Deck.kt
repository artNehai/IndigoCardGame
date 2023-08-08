package indigo

class Deck {

    private val cardDeck: MutableList<Card> =
        Ranks.values().flatMap { rank ->
            Suits.values().map { suit ->
                Card(rank, suit)
            }
        }.shuffled().toMutableList()

    var cardsOnTable: List<Card> = pullCards(INITIAL_CARDS_ON_TABLE)
        private set

    private val playersHand: MutableList<Card> = pullCards(CARDS_IN_HAND).toMutableList()
    private val computersHand: MutableList<Card> = pullCards(CARDS_IN_HAND).toMutableList()

    val handsIsNotEmpty: Boolean
        get() = playersHand.isNotEmpty() || computersHand.isNotEmpty()

    val playersHandSize: Int
        get() = playersHand.size


    private fun pullCards(quantity: Int): List<Card> {
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

    fun playCard(index: Int, contenderId: Int): Card {
        val hand: MutableList<Card> =
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