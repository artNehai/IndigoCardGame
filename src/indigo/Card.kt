package indigo

class Card(
    val rank: Ranks,
    val suit: Suits,
) {
    override fun toString() = "$rank$suit"
}