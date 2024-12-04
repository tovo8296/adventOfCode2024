package day03

import java.io.File

private val regex = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")
private val doPattern = "do()"
private val dontPattern = "don't()"

fun main() {
    var sum = 0
    var startFind = 0
    var match: MatchResult?
    var enabled = true
    do {
        match = regex.find(input, startFind)
        val nextDo = input.indexOf(doPattern, startFind).takeIf { it != -1 } ?: input.length
        val nextDont = input.indexOf(dontPattern, startFind).takeIf { it != -1 } ?: input.length
        val nextMatch = match?.range?.start ?: input.length
        if (nextDo < nextMatch && nextDo < nextDont) {
            enabled = true
            startFind = nextDo + doPattern.length
        }else if (nextDont < nextMatch && nextDont < nextDo) {
            enabled = false
            startFind = nextDont + dontPattern.length
        }else if (match != null){
            if (enabled) {
                val numbers = match.groupValues.drop(1).map { it.toInt() }
                val mult = numbers[0] * numbers[1]
                sum += mult
            }
            startFind = match.range.last + 1
        }
    }while(match != null)
    println("Sum of mults: $sum")
}

val input = File("src/main/kotlin/day03/input").readText()

