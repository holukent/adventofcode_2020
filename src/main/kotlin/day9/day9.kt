package day9

import java.io.File

fun part1(file: List<Long>): Int {
    var result = 0

    for (i in 25..file.lastIndex) {
        val xmas = file.subList(i - 25, i).sorted()
        var l = 0
        var r = xmas.lastIndex
        loop@ while (r > l) {
            when {
                xmas[l] + xmas[r] > file[i] -> r--
                xmas[l] + xmas[r] < file[i] -> l++
                else -> break@loop
            }
        }
        if (l == r) {
            result = i
            break
        }
    }
    return result
}

fun part2(input: List<Long>, index: Int) {

    val target = input[index]
    val list = input.subList(0, index)

    loop@ for (i in 0..list.lastIndex) {
        var r = list.lastIndex + 1
        while (list.subList(i, r).sum() >= target) {
            if (list.subList(i, r).sum() == target) {
                val temp = list.subList(i, r).sorted()
                println(temp.first() + temp.last())
                return
            } else {
                r--
            }
        }
    }
}


fun main() {
    val input = File("src/main/kotlin/day9/input.txt").readLines().map { it.toLong() }
    println(input[part1(input)])
    part2(input, part1(input))

}
