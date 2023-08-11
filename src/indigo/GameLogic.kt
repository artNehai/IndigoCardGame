package indigo

const val INITIAL_CARDS_ON_TABLE = 4
const val CARDS_IN_HAND = 6
const val FINAL_THREE_POINTS = 3

val ranksToPointsMap: Map<Ranks, Int> = mapOf(
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
    val firstToMakeMove =
        if (isPlayerFirst) player
        else computer

    print("Initial cards on the table: ")
    println(cardDeck.initialTable)

    var isPlayersTurn = isPlayerFirst
    while (player.handIsNotEmpty || computer.handIsNotEmpty) {
        printTableStatus(cardDeck)
        if (isPlayersTurn) {
            player.makeMove()
            isPlayersTurn = false
        } else {
            computer.makeMove()
            isPlayersTurn = true
        }

        if (cardDeck.isTableEmpty) {
            printScoreAndCardsWon(player, computer)
        }
    }

    printTableStatus(cardDeck)
    cardDeck.distributeRemainingTable(firstToMakeMove)
    assignFinalPoints(player, computer, firstToMakeMove)
    printScoreAndCardsWon(player, computer)
    println("Game Over")
}

fun printTableStatus(cardDeck: Deck) {
    println()
    println(
        if (cardDeck.isTableEmpty) {
            "No cards on the table"
        } else {
            "${cardDeck.tableSize} cards on the table, and the top card is ${cardDeck.topCard}"
        }
    )
}

fun printScoreAndCardsWon(player: Player, computer: Computer) {
    println("Score: Player ${player.score} - Computer ${computer.score}")
    println("Cards: Player ${player.cardsWonTally} - Computer ${computer.cardsWonTally}")
}

fun assignFinalPoints(
    player: Player,
    computer: Computer,
    firstToMakeMove: Contender,
) {
    when {
        player.cardsWonTally > computer.cardsWonTally -> player
        player.cardsWonTally < computer.cardsWonTally -> computer
        else -> firstToMakeMove
    }.updateScore(points = FINAL_THREE_POINTS)
}