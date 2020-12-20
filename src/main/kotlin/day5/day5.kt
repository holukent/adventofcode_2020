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
    val map = hashMapOf<Int, Int>()
    var id: Int
    var max = 0
    for (i in input) {
        id = binary(i.take(7), 127) * 8 + binary(i.takeLast(3), 7)
        map[id] = id
        if (max < id) max = id //part1
    }
    for (i in map.toSortedMap().firstKey()..map.toSortedMap().lastKey()) {
        if (!map.containsKey(i)) {
            println(i)
            return
        }
    }
}

