package day12

import java.io.File
import kotlin.math.abs

fun part1(file: List<Pair<String, Int>>) {
    var index = 0
    var x = 0
    var y = 0
    val news = arrayOf("E", "S", "W", "N")
    var newsindex = 0
    while (index <= file.lastIndex) {
        when (file[index].first) {
            "F" -> when (news[newsindex]) {
                "E" -> x += file[index].second
                "S" -> y -= file[index].second
                "W" -> x -= file[index].second
                "N" -> y += file[index].second
            }
            "E" -> x += file[index].second
            "S" -> y -= file[index].second
            "W" -> x -= file[index].second
            "N" -> y += file[index].second
            "R" -> newsindex = (newsindex + file[index].second / 90) % 4
            "L" -> newsindex = ((newsindex - file[index].second / 90) + 4) % 4
        }
        index++
    }
    println(abs(x) + abs(y))
}

fun part2(file: List<Pair<String, Int>>) {
    var waypoint = Pair(10, 1)
    var x = 0
    var y = 0
    var index = 0
    while (index <= file.lastIndex) {
        when (file[index].first) {
            "F" -> {
                x += file[index].second * waypoint.first
                y += file[index].second * waypoint.second
            }
            "E" -> waypoint = Pair(waypoint.first + file[index].second, waypoint.second)
            "W" -> waypoint = Pair(waypoint.first - file[index].second, waypoint.second)
            "N" -> waypoint = Pair(waypoint.first, waypoint.second + file[index].second)
            "S" -> waypoint = Pair(waypoint.first, waypoint.second - file[index].second)

            else -> {
                waypoint = when (file[index]) {
                    Pair("R", 90), Pair("L", 270) -> Pair(waypoint.second, waypoint.first * -1)
                    Pair("R", 270), Pair("L", 90) -> Pair(waypoint.second * -1, waypoint.first)
                    else -> Pair(waypoint.first * -1, waypoint.second * -1)
                }
            }
        }
        index++
    }
    println(abs(x) + abs(y))
}

fun main() {
    val input = File("src/main/kotlin/day12/input.txt").readLines().map { Pair(it.take(1), it.drop(1).toInt()) }
    part1(input)
    part2(input)
}