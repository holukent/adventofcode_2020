package day7

import java.io.File

fun part1(input: List<String>, target: String, set: Set<String> = setOf()): Set<String> {
    val temp = input.filter {
        it.contains(target) && it.split(" bags contain ")[0] != target
    }.map { it.split(" bags contain ")[0] }

    val result = (temp + set).toMutableSet()
    return if (temp.isEmpty()) result else {
        for (t in temp) {
            result += part1(input, t, result)
        }
        result
    }
}

fun part2(input: List<String>, target: String, num: Int = 0): Int {
    val temp = input.filter {
        it.contains(target) && it.split(" bags contain ")[0] == target
    }.map { it.split(" bags contain ") }

    val total = temp[0][1].split(" ").sumBy { it.toIntOrNull() ?: 0 } + num

    return if (temp[0][1] == "no other bags.") num else {
        total + temp[0][1].split(", ").sumBy {
            it.split(" ")[0].toInt() * part2(input, it.split(" ").subList(1, 3).joinToString(" "), num)
        }
    }


}

fun main() {
    val input = File("src/main/kotlin/day7/input.txt").readLines()
    val target = "shiny gold"
    println(part1(input, target).size)
    println(part2(input, target))

}