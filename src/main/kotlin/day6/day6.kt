package day6

import java.io.File


fun main() {
    var set = mutableSetOf<Char>()
    var sum = 0
    File("src/main/kotlin/day6/input.txt").forEachLine { s ->
        if (s.isNotBlank()) {
            s.forEach { set.add(it) }
        } else {
            sum += set.size
            set = mutableSetOf()
        }
    }
    println(sum + set.size)
}