package day03

import java.io.File

private val regex = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")

fun main() {
    var sum = 0
    var startFind = 0
    var match: MatchResult?
    do {
        match = regex.find(input, startFind)
        if (match != null){
            val numbers = match.groupValues.drop(1).map { it.toInt() }
            val mult = numbers[0] * numbers[1]
            sum += mult
            startFind = match.range.last + 1
        }
    }while(match != null)
    println("Sum of mults: $sum")
}

val input = File("src/main/kotlin/day03/input").readText()

