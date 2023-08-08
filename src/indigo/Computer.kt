package indigo

class Computer(
    private val cardDeck: Deck
) : Contender() {

    fun makeMove() {
        val cardPlayed = cardDeck.playCard(0, COMPUTER_ID)
        println("Computer plays $cardPlayed")
        println()
    }
}