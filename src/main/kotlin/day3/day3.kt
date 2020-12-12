package day3

import java.io.File

fun main() {
    val input = File("src/main/kotlin/adventofcode/day_3.txt").readLines()
    val way = arrayOf(
        intArrayOf(1, 1),
        intArrayOf(3, 1),
        intArrayOf(5, 1),
        intArrayOf(7, 1),
        intArrayOf(1, 2)
    )
    var result = 1L
    val size = input[0].length
    for (i in 0..way.lastIndex) {
        var (x, y) = IntArray(2)
        var tree = 0
        while (y < input.lastIndex) {
            if (x + way[i][0] < size) {
                x += way[i][0]
            } else {
                x = (x + way[i][0]) - size
            }
            y += way[i][1]
            if (input[y][x] == '#') tree++
        }
        result *= tree
    }
    println(result)
}