package util

data class Coord(val x: Int, val y: Int) {
    inline fun forEachAdjacent(block: (coord: Coord) -> Unit) {
        (x - 1..x + 1).forEach { i ->
            block(Coord(i, y - 1))
            block(Coord(i, y + 1))
        }
        block(Coord(x - 1, y))
        block(Coord(x + 1, y))
    }

    fun move(direction: Direction): Coord = Coord(x + direction.dx, y + direction.dy)

    fun <T> get(world: List<List<T>>): T? = world.getOrNull(y)?.getOrNull(x)

    fun isValid(world: List<List<Any>>): Boolean {
        return x >= 0 && y >= 0 && y < world.size && x < world[y].size
    }
}

data class Coord3D(val x: Int, val y: Int, val z: Int)
