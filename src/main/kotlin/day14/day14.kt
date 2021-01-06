package day14

import java.io.File
import kotlin.math.pow

var mask = ""
var mem = ""
fun tobinary(number: Long) = number.toString(2).padStart(36, '0')

fun todecimal(str: String): Long {
    var result = 0.0
    for (s in str.indices) {
        if (str[s] == '1') result += (2.0.pow(str.lastIndex - s))
    }
    return result.toLong()
}

fun part1(input: List<String>): Long {
    val address = mutableMapOf<Long, Long>()
    for (i in input) {
        var result = ""
        if (i.contains("mask")) mask = i.split(" ")[2] else {
            mem = tobinary(i.split(" ")[2].toLong())
            for (m in 0..mask.lastIndex) {
                result += if (mask[m] == 'X') mem[m] else mask[m]
            }
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
            val set = mutableListOf<Double>()
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
