package indigo

class Computer(
    private val cardDeck: Deck,
) : Contender(cardDeck) {

    fun makeMove() {
        // The following print is used to validate the tests
        // println(hand.joinToString(" "))

        val candidateCards: List<Card> =
            hand.filter(cardDeck::checkIfWinnable)

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
                        hand.count { anotherCard ->
                            anotherCard.suit == card.suit
                        } > 1
                    }
                    val cardsWithSameRank: List<Card> = hand.filter { card ->
                        hand.count { anotherCard ->
                            anotherCard.rank == card.rank
                        } > 1
                    }

                    listOf(cardsWithSameSuit, cardsWithSameRank, hand)
                        .first { it.isNotEmpty() }.random()
                }

                candidateCards.size >= 2 -> {
                    val cardsWithSameSuitAsTop: List<Card> = candidateCards.filter { card ->
                        card.suit == cardDeck.topCard?.suit
                    }
                    val cardsWithSameRankAsTop: List<Card> = candidateCards.filter { card ->
                        card.rank == cardDeck.topCard?.rank
                    }

                    listOf(cardsWithSameSuitAsTop, cardsWithSameRankAsTop, hand)
                        .first { it.size >= 2 }.random()
                }

                else -> {
                    throw Exception("The computer has no cards to make a move")
                }
            }

        println("Computer plays $cardToPlay")
        playCard(cardToPlay)
    }
}