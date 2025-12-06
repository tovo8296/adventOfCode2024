package day11

fun main() {
    var stones = mapOf(*input.split(" ").map { it.toLong() }.map { it to 1L }.toTypedArray())

    (1 .. 75).forEach {
        stones = blink(stones)
        println("after $it blinks: ${stones.size} numbers: $stones")
    }

    val sum = stones.values.sum()
    println("Stone count: $sum")
}

fun blink(stones: Map<Long, Long>): Map<Long, Long> {
    val result = mutableMapOf<Long, Long>()
    stones.forEach { number, times ->
        if (number == 0L) {
            addToMap(result, 1L, times)
        }else if (number.toString().length % 2 == 0) {
            val s = number.toString()
            val half = s.length / 2
            val i1 = s.substring(0, half).toLong()
            val i2 = s.substring(half).toLong()
            addToMap(result, i1, times)
            addToMap(result, i2, times)
        }else {
            addToMap(result, number * 2024L, times)
        }
    }
    return result
}

fun addToMap(map: MutableMap<Long, Long>, number: Long,times: Long) {
    val previous = map.getOrElse(number, { 0 })
    map.set(number, previous + times)
}

val input = "4 4841539 66 5279 49207 134 609568 0"