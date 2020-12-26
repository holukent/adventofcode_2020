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
    var timetamp = 0L
    var step = busid[0].second.toLong()
    for (i in 1..busid.lastIndex) {
        while (true) {
            if ((timetamp + busid[i].first) % busid[i].second == 0L) {
                step *= busid[i].second
                break
            } else timetamp += step
        }
    }
    println(timetamp)
}


fun main() {
    val input = File("src/main/kotlin/day13/input.txt").readLines()
    part1(input)
    part2(input[1].split(","))
}
