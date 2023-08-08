package indigo

const val INITIAL_CARDS_ON_TABLE = 4
const val CARDS_IN_HAND = 6
const val USER_ID = 1
const val COMPUTER_ID = 2


fun startGame(cardDeck: Deck, isPlayerFirst: Boolean) {
    val player = Player(cardDeck)
    val computer = Computer(cardDeck)

    print("Initial cards on the table: ")
    println(
        cardDeck.cardsOnTable.joinToString(" ")
    )
    println()

    var isPlayersTurn = isPlayerFirst
    while (cardDeck.handsIsNotEmpty) {
        println(
            "${cardDeck.cardsOnTable.size} cards on the table, and the top card is ${cardDeck.cardsOnTable.last()}"
        )
        if (isPlayersTurn) {
            player.makeMove()
            isPlayersTurn = false
        } else {
            computer.makeMove()
            isPlayersTurn = true
        }
    }

    println(
        "${cardDeck.cardsOnTable.size} cards on the table, and the top card is ${cardDeck.cardsOnTable.last()}"
    )
    println("Game Over")
    return
}
