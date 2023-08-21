package indigo

open class Contender(
    private val cardDeck: Deck,
) {

    protected val hand: MutableList<Card> = cardDeck.pullCards(CARDS_IN_HAND)

    private val cardsWon = mutableListOf<Card>()

    val handIsNotEmpty: Boolean
        get() = hand.isNotEmpty()

    var score = 0
        protected set

    val cardsWonTally: Int
        get() = cardsWon.size


    protected fun playCard(card: Card) {
        val gain: List<Card> = cardDeck.play(card)

        if (gain.isNotEmpty()) {
            println("${this.javaClass.simpleName} wins cards")
            cardDeck.lastWinner = this
            updateScore(gain)
        }

        hand.remove(card)
        if (hand.isEmpty()) {
            hand.addAll(
                cardDeck.pullCards(CARDS_IN_HAND)
            )
        }
    }

    protected fun printHand() {
        print("Cards in hand: ")
        for (i in hand.indices) {
            print("${i + 1})" + hand[i] + " ")
        }
        println()
    }

    internal fun updateScore(gain: List<Card>) {
        for (card in gain) {
            score += ranksToPointsMap[card.rank] ?: 0
        }
        cardsWon.addAll(gain)
    }

    internal fun updateScore(points: Int) {
        score += points
    }
}