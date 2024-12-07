package day06

import util.Coord
import util.Direction
import java.lang.RuntimeException

fun main() {
    val map = input.lines().map { it.toList() }
    var guardPos = findGuard(map)
    var direction = Direction.N
    val watchedPoints = mutableSetOf<Coord>()
    while(guardPos.isValid(map)) {
        watchedPoints.add(guardPos)
        var next = guardPos.move(direction)
        if (next.get(map) == '#') {
            direction = direction.rotateRight90()
            next = guardPos.move(direction)
        }
        guardPos = next
    }
    println("Watched points: ${watchedPoints.size}")

}

fun findGuard(map: List<List<Char>>): Coord {
    map.forEachIndexed { y, line ->
        val x = line.indexOf('^')
        if (x != -1) {
            return Coord(x, y)
        }
    }
    throw RuntimeException("Guard not found")
}

val input = """
.......................#...................#....#..#......#.................#.#..#........................#.#..............#.....#
................#.#.......#..#.......#.................................................................................#.##.......
............#..............#......................#...#.....#...#..........##.........#.....#.....................................
..#...#......................................#............................#..................#..........#..#......................
....#........#.#....................#..........#.....#.............#..#............................#..................#......#....
..........................................................#................#............................................#.........
..#............................#.....#...........................#...........#........##...........................#..#...........
.....................#...........................#.#..............#......................................#.........#....#.....#...
.#...#.#.................#....................#...............................................................#.#..........#......
....#....................................#...........................................#.....#..........#...........................
.......................#..........#..............#......#....#.......#........................................#.#.................
......#...........................................................#...#............#.................#............................
.......#.................#..#.....................#...............................................................................
..............#....................#.#..........#........#......#................#.................#..........#...................
#.#.......................................#.........................#...................................#.........................
...#.........................................................................................#................#..................#
#.....#....#............................................................#.........................#..................#.#..........
.#.....#.#.......#.#..................#.#......................#.......#.....#..#..................##.....#.......#...#...........
....#.........#.................................#..........#........#...............#................#...................#....#...
.................#...............#...#.................#.................................................#........................
....#...#...............................#..................................................#..........#................#.....#....
...........#........#....#.......#...#..........#...............#.....#.....#.....#..........#....................................
............#.........#.................#................#.................#.#..................................................#.
............#......................................#...................................................................#..........
...........#....#.....................................................................................#............#..........#...
..........#........................................#.............#.................#........................#..#.......#........#.
......#.............................#.................##..................................#.....................#.................
.#.................................#.............................................#.................##.....#..............#......#.
.........................#..............................#..........#...........#....#................#......................#.....
.......#...................#......##........................#.........#...........................................................
......................................................................##...........#................#.............................
....#...................................##...............................................#........................................
...##.....#..#........#.................#..............................................................#..........................
........##......#....................#..............................#......................#...................#..................
.........................................................#.......................................................#.#..............
..........#.............................#.........#...............#................#.#.........#..........................#.#.....
....................#...................#..#.......................................................#..........#...............#...
...........................................................#........................#.........#........#..........................
.......#..............#......#.................................#...................##...........................#.........#.......
#...#...............................................................................................................#.............
................................#..#....#............#...........#..................#.............................#...............
....................#..............#.........#.................................#.............#..##................................
#...#..................................................................................#.....#...........#........................
........#...........#........#...#..................#.....#....#.........#.........................#...........................#..
.............#...#...................................................................#...........#................................
.............#........................................#...#.............#..............................#..#.........#.............
...#.................................................................................#.............#..........................##..
...#..............................................................................................................................
................#.........#..................................#...................#................................................
...#................#...#.#.....................#...........#..................................#........#....#......#.............
..........................#.....................#..#...................................##........................................#
#........................#...............................................#........#..#..........#...........................#.....
.#............#.#..............................................................#...........#......................................
......#..................................................................#.......#..............................#...#....#........
........#........................#.........#.............#.....#..................................................................
...............................................................................#...........................................#......
........................#..................................#.....................................................#................
............................................................#..........................#.................#.......................#
.....................#..#.........................#..............#.............................#........#..............##......#..
.....................#.................................................#.......#............#........................#.......#....
......................#.....................................................#......#............................#.................
................................................................#...#..##..#.....................................#...............#
..............#..............................................................................................#....................
............................................#......#..........#...............................................................#...
....#.................................#..................................................................#..........##..#.........
............#.....#................................................................................#..........#.........#.........
.....................#......................................#.........#....................................................#....#.
.........................#...............................#...............................#....................#...................
.#.....................#.#.......................................#....#...........#.......................#.......................
.....................................................#.#..#......................................................................#
#..........#..........................#............................#.............#..................#.............#...........#...
........#.........................................................................................................................
..........#.............#.........................................#................................................#..............
...........#................................#.................#...........................#.............................#.........
..................#.........................................#.#..................#...#...........#.......#..#.....................
........................................................#...#.......................................................#.............
.........................................................................#.......#..#.....#.......................................
............#..............................................................#.........#..............#.............#...............
#...#..........................................#...........#.....#.....................................#...............#..........
...........................#............#.........................................................................................
...#..........................................#.......................................................................#...........
..............#......#.....#.....................#..........................#.....................................................
.........................#....#...................#...#.......#..............................#........................#...........
.............................#............^.#................................................#......#..#.#...........#............
....................................................................................#...#.#...................................#...
.......................#..............................##..............................................#...........................
......................#...................................................#..........................................#............
......#........................................................................#.....................................#............
.......................#.............................#........................#...........#...........#............#...........#..
....#...............#..#......#....#..................#.............#.......#...............#.......................#.............
............#...#..................#..............................................................................................
....#.......#.................#.......................................................#..#......#.................................
.........#......#..........#......................#.....................#..................#.#....................................
...............##.......................................................#...#.............................#.......#...............
........................................................##...................#...#............#......#............................
...........#...#..............#.............................................................#...................#............#....
...............................................#.....#.....#......................................................................
.#....................#...#........#....................#.#.........#..........#................................#................#
.............#.....#..#....................................#.#...........#.................................................#..#.#.
......#.....................................#....................................................................#................
............................................................##......#.....................#.......#.....................#...#.....
.#.......#......................#.................................................................................................
................#....#......................#.........#...................#........#.......##.....#...............................
..#....#....................#............................................................#.#......#............#.....#.#....#.....
.................................#.................................................#.......#......................................
..................#...........#....#.....................#......#...............#..................#....................##........
.##...............................................#............#...........................#......................................
.............#...........#...........................#..#.......#...#..........#......................#..........#................
.......#...................................#.................#....#..............................................................#
........#....................................................#.................#.....#.........#.#................................
................#.................................#.....................#.....#.....#............................##...............
..............................................##.................#..##........#...........#............#..........................
....#......................................#...............................................................................#......
..#..............#.#................#..#..............#.......................................#.#.......#..............#......#..#
.....#..............................................................................................................#.......#.....
..#.......#.#..........#.......#..................................#..........#.......#.................#...........#...........#..
...............#.....#................#............#................#............#.....#.#........#...............................
....#.....................#................#.....................................#......................#.#.......#....#..........
........#...............#...#.....................................................#........#.........#.........#..................
...#......#..........................#.....#..........................##.......#....#.............................................
.............#................................#............................#.....#.......#......#................................#
.......................#............#..........................#..................................................................
.................................................#..............#...#...........................#.................................
......#...................#..............................................................................................#....#..#
....................................#......#.....................#..............#.......#...................#.....................
............#...................................#...........#........#........#...........................................##......
#...........#..........................#....................................#.........#................................#..........
......##..#...............................................................#...............................#.........#.............
.......................#.....#.........................#...#.........................#.....#..........#.#...#.....................
.......................#..#.....#.....#..........................#.........#.....#....................#.....#....................#
""".trimIndent()