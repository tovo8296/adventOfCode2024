package util

enum class Direction(val dx: Int, val dy: Int) {
    N(0, -1),
    S(0, 1),
    W(-1, 0),
    E(1, 0),

    NW(-1, -1),
    NE(1, -1),
    SW(-1, 1),
    SE(1, 1);

    companion object {
        val straightEntries = listOf(N, W, S, E)
    }

    fun rotateRight90(): Direction = when(this) {
        N -> E
        E -> S
        S -> W
        W -> N

        NW -> NE
        NE -> SE
        SE -> SW
        SW -> NW
    }

    fun rotateLeft90(): Direction = when(this) {
        N -> W
        W -> S
        S -> E
        E -> N

        NW -> SW
        SW -> SE
        SE -> NE
        NE -> NW
    }
}