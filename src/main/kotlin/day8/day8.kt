package day8

import java.io.File


fun part1(
    file: MutableMap<Int, Pair<String, Int>>,
    index: Int = 0,
    map: MutableMap<Int, Pair<String, Int>> = mutableMapOf(),
): MutableMap<Int, Pair<String, Int>> {

    val (operation, argument) = file[index]!!
    var i = index
    map[i] = Pair(operation, argument)
    if (operation == "jmp") i += map[i]!!.second else i++

    return when {
        i == file.keys.last() -> {
            map[i] = Pair(file.values.last().first, file.values.last().second)
            map
        }
        !map.containsKey(i) -> part1(file, i, map)
        else -> map
    }
}

fun part2(
    file: MutableMap<Int, Pair<String, Int>>,
    map: MutableMap<Int, Pair<String, Int>>,
): MutableMap<Int, Pair<String, Int>> {

    loop@ for (m in map) {

        when (m.value.first) {

            "jmp" -> {
                file[m.key] = Pair("nop", file[m.key]!!.second)
                if (part1(file).keys.last() == file.keys.last()) return part1(file) else {
                    file[m.key] = Pair("jmp", file[m.key]!!.second)
                    continue@loop
                }
            }
            "nop" -> {
                file[m.key] = Pair("jmp", file[m.key]!!.second)
                if (part1(file).keys.last() == file.keys.last()) return part1(file) else {
                    file[m.key] = Pair("nop", file[m.key]!!.second)
                    continue@loop
                }
            }
            "acc" -> continue@loop
        }
    }
    return map
}

fun main() {
    val input = mutableMapOf<Int, Pair<String, Int>>()
    File("src/main/kotlin/day8/input.txt").readLines().forEachIndexed { index, s ->
        input[index] = Pair(s.split(" ")[0], s.split(" ")[1].toInt())
    }

    println(part1(input).filter { it.value.first == "acc" }.map { it.value.second }.sum())
    println(part2(input, part1(input)).filter { it.value.first == "acc" }.map { it.value.second }.sum())

}