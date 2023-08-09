package indigo

const val INITIAL_CARDS_ON_TABLE = 4
const val CARDS_IN_HAND = 6

val ranksToPoints: Map<Ranks, Int> = mapOf(
    Ranks.ACE to 1,
    Ranks.KING to 1,
    Ranks.QUEEN to 1,
    Ranks.JACK to 1,
    Ranks.TEN to 1,
)


fun startGame(
    cardDeck: Deck,
    isPlayerFirst: Boolean,
) {
    val player = Player(cardDeck)
    val computer = Computer(cardDeck)

    print("Initial cards on the table: ")
    println(cardDeck.initialTable)

    var isPlayersTurn = isPlayerFirst
    while (player.handIsNotEmpty && computer.handIsNotEmpty) {
        printTableStatus(cardDeck)
        if (isPlayersTurn) {
            player.makeMove()
            isPlayersTurn = false
        } else {
            computer.makeMove()
            isPlayersTurn = true
        }

        if (cardDeck.tableIsEmpty) {
            printScoreAndCardsWon(player, computer)
        }
    }

    printTableStatus(cardDeck)
    println("Game Over")
    return
}

fun printTableStatus(cardDeck: Deck) {
    println()
    println(
        if (cardDeck.tableIsEmpty) {
            "No cards on the table"
        }
        else {
            "${cardDeck.tableSize} cards on the table, and the top card is ${cardDeck.topCard}"
        }
    )
}

fun printScoreAndCardsWon(player: Player, computer: Computer) {
    println("Score: Player ${player.score} - Computer ${computer.score}")
    println("Cards: Player ${player.cardsWonTally} - Computer ${computer.cardsWonTally}")
}
