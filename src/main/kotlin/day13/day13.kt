package day13

import java.io.File

fun part1(file: List<String>) {
    val timestamp = file[0].toInt()
    val busid = file[1].split(",")
        .filter { it != "x" }
        .map { it.toInt() }
        .map { Pair(it, if (timestamp % it != 0) ((timestamp / it) + 1) * it - timestamp else timestamp) }
        .sortedBy { it.second }
    println(busid[0].first * busid[0].second)
}

fun part2(file: List<String>) {
    val busid = file.filter { it != "x" }.map { Pair(file.indexOf(it), it.toInt()) }
    var result = 0L
    var timetamp = busid[0].second.toLong()
    for (i in 1..busid.lastIndex) {
        while (true) {
            if ((result + busid[i].first) % busid[i].second == 0L) {
                timetamp *= busid[i].second
                break
            } else result += timetamp
        }
    }
    println(result)
}


fun main() {
    val input = File("src/main/kotlin/day13/input.txt").readLines()
    part1(input)
    part2(input[1].split(","))
}
