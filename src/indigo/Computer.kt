package indigo

class Computer(
    cardDeck: Deck,
) : Contender(cardDeck) {

    fun makeMove() {
        val card = hand[0]
        playCard(card)
        println("Computer plays $card")
    }
}