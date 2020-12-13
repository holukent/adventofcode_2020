package day6

import java.io.File


fun main() {
    var map = hashMapOf<String,String>()
    var size = 0
    var sum = 0
    File("src/main/kotlin/day6/input.txt").forEachLine { s ->
        size++
        if (s.isNotBlank()) {
            s.forEach {
                if (map["$it"] == null) map["$it"] = "$it" else map["$it"] = map["$it"] + it
            }
        } else {
            map.forEach { (_, v) ->
                if (v.length == size - 1) sum++
            }
            size = 0
            map.clear()
        }
    }
    map.forEach { (k, v) ->
        if (v.length == size) sum++
    }

    println(sum)

}