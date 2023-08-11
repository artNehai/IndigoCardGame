package indigo

class Card(
    val rank: Rank,
    val suit: Suit,
) {
    override fun toString() = "$rank$suit"
}