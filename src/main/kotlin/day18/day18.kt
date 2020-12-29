package day18

import java.io.File

fun op(input: String): Long {
    val list = input.split(" ")
    var result = list[0].toLong()
    for (j in 1..list.lastIndex) {
        if (list[j].toIntOrNull() == null) {
            when (list[j]) {
                "+" -> result += list[j + 1].toLong()
                "*" -> result *= list[j + 1].toLong()
            }
        }
    }
    return result
}

fun part1(input: List<String>) {
    val stack = mutableListOf<String>()
    var sum = 0L
    for (i in input) {
        for (j in i.lastIndex downTo 0) {
            when (i[j].toString()) {
                "(" -> {
                    var temp = ""
                    while (true) {
                        temp += stack.last()
                        stack.removeLast()
                        if (stack.last() == ")") {
                            stack.removeLast()
                            break
                        }
                    }
                    stack.add(op(temp).toString())
                }
                else -> stack.add(i[j].toString())
            }
        }
        var temp = ""
        while (true) {
            temp += stack.last()
            stack.removeLast()
            if (stack.isEmpty()) break
        }
        sum += op(temp)
    }
    println(sum)
}

fun main() {
    val input = File("src/main/kotlin/day18/input.txt").readLines()
    part1(input)
    part2(input)
}

fun part2(input: List<String>) {

    var sum = 0L
    for (i in input) {
        val stack = mutableListOf<String>()
        var result = 1L
        for (j in i.indices) {
            when (val it = i[j].toString()) {
                " " -> continue
                "*", "+", "(" -> stack.add(it)
                ")" -> {
                    var temp = 1L
                    while (stack.last() != "(") {
                        if (stack.last().toIntOrNull() != null) temp *= stack.last().toLong()
                        stack.removeLast()
                    }
                    stack.removeLast()
                    if (stack.isEmpty() || stack.last() != "+") {
                        stack.add(temp.toString())
                    } else {
                        stack.removeLast()
                        temp += stack.last().toLong()
                        stack.removeLast()
                        stack.add(temp.toString())
                    }
                }

                else -> {
                    if (stack.isEmpty() || stack.last() != "+") stack.add(it)
                    else {
                        stack.removeLast()
                        val temp = (stack.last().toLong() + it.toLong()).toString()
                        stack.removeLast()
                        stack.add(temp)
                    }

                }
            }
        }
        stack.forEach { if (it != "*") result *= it.toLong() }
        sum += result
    }
    println(sum)
}


