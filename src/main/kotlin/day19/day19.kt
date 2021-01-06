package day19

import java.io.File


//fun part1(rulesmap: Array<String?>, list: MutableList<String>) {
//    val rulezero = rulesmap[0]!!.split(" ")
//    val a = rulesmap.indexOf("\"a\"")
//    val b = rulesmap.indexOf("\"b\"")
//    while (true) {
//        if (!rulezero.contains(rulesmap[a]) || !rulezero.contains(rulesmap[b]) )
//    }
//
//
//}
fun main() {
    val rulesmap = arrayOfNulls<String>(136)
    val list = mutableListOf<String>()

    val input = File("src/main/kotlin/day19/input.txt").readText().split("\n\r")
    input[0].split("\n").map { rulesmap[it.split(": ")[0].toInt()] = it.split(": ")[1].trim()}
    input[1].split("\n").forEach { list.add(it) }

//    part1(rulesmap,list)

}