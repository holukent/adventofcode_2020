package day14

import java.io.File
import kotlin.math.pow

var mask = ""
var mem = ""
fun tobinary(number: Long) = number.toString(2).padStart(36, '0')

fun todecimal(str: String): Long {
    var result = 0.0
    str.forEachIndexed { index, c -> if (c == '1') result += (2.0.pow(str.lastIndex - index)) }
    return result.toLong()
}

fun part1(input: List<String>): Long {
    val address = mutableMapOf<Long, Long>()
    for (i in input) {
        if (i.contains("mask")) mask = i.split(" ")[2] else {
            var result = ""
            mem = tobinary(i.split(" ")[2].toLong())
            mask.forEachIndexed { index, c -> result += if (c == 'X') mem[index] else mask[index] }
            address[i.split(" ")[0].drop(4).dropLast(1).toLong()] = todecimal(result)
        }
    }
    return address.values.sum()
}

fun part2(input: List<String>): Long {
    val address = mutableMapOf<Long, Long>()
    for (i in input) {
        if (i.contains("mask")) mask = i.split(" ")[2] else {
            mem = tobinary(i.split(" ")[0].drop(4).dropLast(1).toLong())
            var result = 0.0
            val set = mutableSetOf<Double>()
            mask.forEachIndexed { index, c ->
                when {
                    c == 'X' -> set.add(2.0.pow(35 - index))
                    c == '1' || mem[index] == '1' -> result += 2.0.pow(35 - index)
                }
            }
            val list = mutableSetOf<Double>()
            list.add(result)
            for (s in set) {
                val temp = mutableSetOf<Double>()
                list.forEach { temp.add(it + s) }
                list.addAll(temp)
            }
            list.forEach { address[it.toLong()] = i.split(" ")[2].toLong() }
        }
    }
    return address.values.sum()
}

fun main() {
    val input = File("src/main/kotlin/day14/input.txt").readLines()
    println(part1(input))
    println(part2(input))
}
