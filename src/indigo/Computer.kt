package indigo

class Computer(
    private val cardDeck: Deck,
) : Contender(cardDeck) {

    fun makeMove() {
        // The following output is used to validate the tests
        // println(hand.joinToString(" "))

        val candidateCards: List<Card> =
            hand.filter { cardDeck.checkIfWinnable(it) }

        val cardToPlay: Card =
            when {
                hand.size == 1 -> {
                    hand.first()
                }

                candidateCards.size == 1 -> {
                    candidateCards.first()
                }

                cardDeck.isTableEmpty || candidateCards.isEmpty() -> {
                    val cardsWithSameSuit: List<Card> = hand.filter { card ->
                        hand.count {
                            it.suit == card.suit
                        } > 1
                    }
                    val cardsWithSameRank: List<Card> = hand.filter { card ->
                        hand.count {
                            it.rank == card.rank
                        } > 1
                    }

                    if (cardsWithSameSuit.isNotEmpty()) {
                        cardsWithSameSuit.random()
                    } else if (cardsWithSameRank.isNotEmpty()) {
                        cardsWithSameRank.random()
                    } else {
                        hand.random()
                    }
                }

                candidateCards.size >= 2 -> {
                    val cardsWithSameSuitAsTop: List<Card> = candidateCards.filter { card ->
                        card.suit == cardDeck.topCard?.suit
                    }
                    val cardsWithSameRankAsTop: List<Card> = candidateCards.filter { card ->
                        card.rank == cardDeck.topCard?.rank
                    }

                    if (cardsWithSameSuitAsTop.size >= 2) {
                        cardsWithSameSuitAsTop.random()
                    } else if (cardsWithSameRankAsTop.size >= 2) {
                        cardsWithSameRankAsTop.random()
                    } else {
                        candidateCards.random()
                    }
                }

                else -> {
                    throw Exception("The computer has no cards to make a move")
                }
            }

        println("Computer plays $cardToPlay")
        playCard(cardToPlay)
    }
}