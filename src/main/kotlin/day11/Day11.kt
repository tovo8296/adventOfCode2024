package day11

fun main() {
    val stones = input.split(" ").map { it.toLong() }.toMutableList()
//    println("init: ${stones.joinToString()}")
    (1 .. 25).forEach {
        blink(stones)
//        println("after $it blinks: ${stones.joinToString()}")
    }
    println("Stone count: ${stones.size}")
}

fun blink(stones: MutableList<Long>) {
    val iter = stones.listIterator()
    while(iter.hasNext()) {
        val next = iter.next()
        if (next == 0L) {
            iter.set(1L)
        }else if (next.toString().length % 2 == 0) {
            val s = next.toString()
            val half = s.length / 2
            val i1 = s.substring(0, half).toLong()
            val i2 = s.substring(half).toLong()
            iter.set(i1)
            iter.add(i2)
        }else {
            iter.set(next * 2024L)
        }
    }
}

val input = "4 4841539 66 5279 49207 134 609568 0"