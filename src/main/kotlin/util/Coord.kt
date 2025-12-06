package util

import java.util.Comparator

data class Coord(val x: Int, val y: Int) {
    inline fun forEachAdjacent(block: (coord: Coord) -> Unit) {
        (x - 1..x + 1).forEach { i ->
            block(Coord(i, y - 1))
            block(Coord(i, y + 1))
        }
        block(Coord(x - 1, y))
        block(Coord(x + 1, y))
    }

    inline fun forEachStraightNeighbor(block: (coord: Coord) -> Unit) {
        Direction.straightEntries.forEach { dir ->
            block(move(dir))
        }
    }

    fun move(direction: Direction): Coord = Coord(x + direction.dx, y + direction.dy)

    fun <T> get(world: List<List<T>>): T? = world.getOrNull(y)?.getOrNull(x)

    fun <T> set(world: List<MutableList<T>>, value: T) = world.get(y).set(x, value)

    fun isValid(world: List<List<Any>>): Boolean {
        return x >= 0 && y >= 0 && y < world.size && x < world[y].size
    }
}

data class Coord3D(val x: Int, val y: Int, val z: Int)

class CoordComparator(val linesFirst: Boolean): Comparator<Coord> {
    override fun compare(c1: Coord?, c2: Coord?): Int {
        if (c1 ==  null && c2 == null) {
            return 0
        }else if (c1 == null) {
            return 1
        }else if (c2 == null) {
            return -1
        }
        val xd = c1.x - c2.x
        val yd = c1.y - c2.y
        val d1 = if(linesFirst) yd else xd
        val d2 = if(linesFirst) xd else yd
        return if (d1 != 0) d1 else d2
    }

}