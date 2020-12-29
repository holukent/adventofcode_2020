package day16

import java.io.File

fun part1(file: List<List<String>>, nearbyticket: List<List<String>>) {
    var result = 0
    for (n in nearbyticket) {
        loop@ for (i in n) {
            for (f in file) {
                if (i.toInt() in f[0].split("-")[0].toInt()..f[0].split("-")[1].toInt() ||
                    i.toInt() in f[1].split("-")[0].toInt()..f[1].split("-")[1].toInt()
                ) {
                    continue@loop
                } else if (f == file.last()) {
                    result += i.toInt()
                }
            }
        }
    }
    println(result)
}

fun part2(file: List<List<String>>, yourticket: List<String>, nearbyticket: List<List<String>>) {
    val validticket = mutableListOf<List<String>>()
    looop@ for (n in nearbyticket) {
        loop@ for (i in n) {
            for (f in file) {
                if (i.toInt() in f[0].split("-")[0].toInt()..f[0].split("-")[1].toInt() ||
                    i.toInt() in f[1].split("-")[0].toInt()..f[1].split("-")[1].toInt()
                ) {
                    continue@loop
                } else if (f == file.last()) continue@looop
            }
        }
        validticket.add(n)
    }
    val invaildmap = mutableMapOf<Int, Pair<Int, Int>>()
    file.forEachIndexed { index, list ->
        val temp = list.joinToString().split("-")[1].split(", ")
        invaildmap.put(index, Pair(temp[0].toInt(),temp[1].toInt()))
    }
    val invalidarray = mutableMapOf<Int,MutableSet<Int>>()
    invaildmap.forEach { t, u ->
        for (v in validticket.indices) {
            for (i in validticket[v].indices) {
                if (validticket[v][i].toInt() in u.first-1 until u.second) {

                }
            }
        }
    }




}


fun main() {
    val input = File("src/main/kotlin/day16/input.txt").readLines()
        .map { it.split(": ").drop(1).joinToString() }
        .map { it.split(" or ") }
    val yourticket = File("src/main/kotlin/day16/yourticket.txt").readText().split(",")
    val nearbyticket = File("src/main/kotlin/day16/nearbyticket.txt").readLines().map { it.split(",") }

//    part1(input, nearbyticket)
    part2(input, yourticket, nearbyticket)

}