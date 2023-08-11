package indigo

class Computer(
    cardDeck: Deck,
) : Contender(cardDeck) {

    fun makeMove() {
        print("Computer's hand: ")
        for (i in hand.indices) {
            print("${i + 1})" + hand[i] + " ")
        }
        println()
        val card = hand[0]
        println("Computer plays $card")
        playCard(card)
    }
}