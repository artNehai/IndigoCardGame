package indigo

class Deck {

    private val cardDeck: MutableList<Card> =
        Ranks.values().flatMap { rank ->
            Suits.values().map { suit ->
                Card(rank, suit)
            }
        }.shuffled().toMutableList()

    private val cardsOnTable: MutableList<Card> = pullCards(INITIAL_CARDS_ON_TABLE)

    val initialTable: String = cardsOnTable.joinToString(" ")

    val topCard: Card
        get() = cardsOnTable.last()

    val tableSize: Int
        get() = cardsOnTable.size

    val tableIsEmpty: Boolean
        get() = cardsOnTable.isEmpty()


    internal fun pullCards(quantity: Int): MutableList<Card> {
        val cardsList = cardDeck.take(quantity).toMutableList()
        cardDeck.removeAll(cardsList)

        return cardsList
    }

    fun play(cardPlayed: Card): List<Card> {
        val topCard = cardsOnTable.lastOrNull()
        cardsOnTable.add(cardPlayed)

        if (cardPlayed.suit == topCard?.suit ||
            cardPlayed.rank == topCard?.rank
        ) {
            val cardsWon = cardsOnTable.toList()
            cardsOnTable.clear()
            return cardsWon
        } else {
            return emptyList()
        }
    }
}