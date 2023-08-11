package indigo

class Deck {

    private val cardDeck: MutableList<Card> =
        Ranks.values().flatMap { rank ->
            Suit.values().map { suit ->
                Card(rank, suit)
            }
        }.shuffled().toMutableList()

    private val cardsOnTable: MutableList<Card> = pullCards(INITIAL_CARDS_ON_TABLE)

    val initialTable: String = cardsOnTable.joinToString(" ")

    val topCard: Card?
        get() = cardsOnTable.lastOrNull()

    val tableSize: Int
        get() = cardsOnTable.size

    val isTableEmpty: Boolean
        get() = cardsOnTable.isEmpty()

    internal var lastWinner: Contender? = null


    internal fun pullCards(quantity: Int): MutableList<Card> {
        val cardsList = cardDeck.take(quantity).toMutableList()
        cardDeck.removeAll(cardsList)

        return cardsList
    }

    fun play(cardPlayed: Card): List<Card> {
        if (checkIfWinnable(cardPlayed)) {
            val cardsWon = cardsOnTable + cardPlayed
            cardsOnTable.clear()
            return cardsWon
        } else {
            cardsOnTable.add(cardPlayed)
            return emptyList()
        }
    }

    fun distributeRemainingTable(firstToMakeMove: Contender) {
        (lastWinner ?: firstToMakeMove)
            .updateScore(cardsOnTable)
        cardsOnTable.clear()
    }

    fun checkIfWinnable(card: Card): Boolean {
        return card.suit == topCard?.suit || card.rank == topCard?.rank
    }
}