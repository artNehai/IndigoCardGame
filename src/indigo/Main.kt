package indigo

fun main() {

    val ranks = setOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
    val suits = setOf("♦", "♥", "♠", "♣")
    val deckCards: Set<String> = cartesianProductOfSets(ranks, suits)

    println(ranks.joinToString(" "))
    println(suits.joinToString(" "))
    println(deckCards.joinToString(" "))
}


fun cartesianProductOfSets(
    set1: Set<String>,
    set2: Set<String>,
): Set<String> {

    val resultSet = mutableSetOf<String>()
    for (i in set1) {
        for (j in set2) {
            resultSet.add("$i$j")
        }
    }
    return resultSet
}