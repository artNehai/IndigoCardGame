package indigo

import indigo.Rank.*

const val INITIAL_CARDS_ON_TABLE = 4
const val CARDS_IN_HAND = 6
const val FINAL_THREE_POINTS = 3

val ranksToPointsMap: Map<Rank, Int> = mapOf(
    ACE to 1,
    KING to 1,
    QUEEN to 1,
    JACK to 1,
    TEN to 1,
    NINE to 0,
    EIGHT to 0,
    SEVEN to 0,
    SIX to 0,
    FIVE to 0,
    FOUR to 0,
    THREE to 0,
    TWO to 0,
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
        cardDeck.printTableStatus()
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

    cardDeck.printTableStatus()
    cardDeck.distributeRemainingTable(firstToMakeMove)
    assignFinalPoints(player, computer, firstToMakeMove)
    printScoreAndCardsWon(player, computer)
    println("Game Over")
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
    }.updateScore(FINAL_THREE_POINTS)
}