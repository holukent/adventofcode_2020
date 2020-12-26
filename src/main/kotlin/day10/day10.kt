package day10


import java.io.File

fun part1(file: List<Int>) {
    val array = IntArray(2)
    for (i in 1..file.lastIndex) {
        if (file[i] - file[i - 1] == 1) array[0]++ else array[1]++
    }

    println((array[0]) * (array[1]))
}

fun part2(file: MutableList<Int>) {
    val list = mutableListOf<Int>()
    loop@ for (i in 1..file.lastIndex - 1) {
        if (file[i + 1] - file[i - 1] > 3) continue@loop else list.add(file[i])
    }
    println(list)
}


fun main() {
    val input = File("src/main/kotlin/day10/input.txt").readLines().map { it.toInt() }.sorted().toMutableList()
    input.mapIndexed { index, i -> i - input.getOrElse(index - 1) { 0 } }.plus(3)
        .let { it.count { i -> i == 1 } * it.count { i -> i == 3 } }.let { println(it) }

    input.fold(mapOf(0 to 1L),
        { acc, i -> acc.plus(i to (i - 3 until i).map { acc[it] ?: 0 }.sum()) }).let { println(it) }



}