package day15

import java.io.File

fun main() {
    val input = File("src/main/kotlin/day15/input.txt").readText().split(",").map { it.toInt() }
    println(solution(input, 30000000))
}

fun solution(input: List<Int>, times: Int): Int {
    val result = IntArray(times) { -1 }
    var next = 0
    repeat(times - 1) {
        when {
            it < input.size -> result[input[it]] = it + 1
            result[next] == -1 -> {
                result[next] = it + 1
                next = 0
            }
            else -> {
                val temp = next
                next = it + 1 - result[next]
                result[temp] = it + 1
            }
        }
    }
    return next
}