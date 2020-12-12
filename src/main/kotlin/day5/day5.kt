package day5

import java.io.File

fun binary(str: String, size: Int): Int {
    var l = 0
    var r = size
    for (s in str) {
        val middle = (l + r) / 2
        when (s) {
            'F', 'L' -> {
                r = middle
            }
            'B', 'R' -> {
                l = middle
            }
        }
    }
    return r
}

fun main() {
    val input = File("src/main/kotlin/day5/input.txt").readLines()
    val set = mutableMapOf<Int, Int>()
    var id: Int
    var max = 0
    for (i in input) {
        id = binary(i.take(7), 127) * 8 + binary(i.takeLast(3), 7)
        set[id] = id
        if (max < id) max = id
    }
    for (i in set.toSortedMap().firstKey()..set.toSortedMap().lastKey()) {
        if (!set.containsKey(i)) println(i)
    }
}

