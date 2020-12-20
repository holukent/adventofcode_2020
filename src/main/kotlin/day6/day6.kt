package day6

import java.io.File
fun main() {
    val map = hashMapOf<String,String>()
    var size = 0
    var sum = 0
    File("src/main/kotlin/day6/input.txt").forEachLine { s ->
        if (s.isNotBlank()) {
            size++
            s.forEach {
                if (map["$it"] == null) map["$it"] = "$it" else map["$it"] = map["$it"] + it
            }
            return@forEachLine
        } else {
            map.forEach { (_, v) ->
                if (v.length == size) sum++
            }
            size = 0
            map.clear()
        }

    }
    map.forEach { (_, v) ->
        if (v.length == size) sum++
    }

    println(sum)

}
/*
private fun numYesInGroup(group: String): Int =
    group.filter { it != '\n' }.toSet().size



private fun numAllYesInGroup(group: String): Int =
    group.trim()
        .split('\n')
        .map(String::toSet)
        .reduceRight(Set<Char>::intersect).size

fun main() {
    val data = File("src/main/kotlin/day6/input.txt").readText().split("\n\n")

    println(data.map { numYesInGroup(it) }.sum())
    println(data.map { numAllYesInGroup(it) }.sum())
}*/
