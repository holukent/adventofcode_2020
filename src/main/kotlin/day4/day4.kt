package day4

import java.io.File

fun validyears(str: String, year: Int): Boolean {
    when (str) {
        "byr" -> if (year !in 1920..2002) return false
        "iyr" -> if (year !in 2010..2020) return false
        "eyr" -> if (year !in 2020..2030) return false
    }
    return true
}

fun validhaircolor(str: String): Boolean {
    if (str.substring(1..str.lastIndex).length != 6) return false
    if (str[0] != '#') return false
    val regex = Regex("[^0-9a-f]")
    if (str.substring(1..str.lastIndex).contains(regex)) return false
    return true
}

fun validdhieght(str: String): Boolean {
    val result = if (str.removeRange((str.lastIndex - 1)..str.lastIndex).toIntOrNull() == null) {
        return false
    } else {
        str.removeRange((str.lastIndex - 1)..str.lastIndex).toInt()
    }
    when (str.takeLast(2)) {
        "cm" -> {
            if (result !in 150..193) return false
        }
        "in" -> {
            if (result !in 59..76) return false
        }
        else -> return false
    }
    return true
}

fun validpid(str: String): Boolean {
    if (str.length != 9) return false
    for (s in str) {
        if (!s.isDigit()) return false
    }
    return true
}

fun main() {
    var count = 0
    val list = mutableListOf<String>("")
    File("src/main/kotlin/adventofcode/day4.txt")
        .forEachLine {
            if (it.isNotBlank()) {
                list[list.lastIndex] = (it + " " + list.last()).trim()
            } else {
                list.add("")
            }
        }

    loop@ for (i in list) {

        looop@ for (j in i.split(" ")) {

            if (i.split(" ").size > 6) {
                val jone = j.split(":")
                when (jone[0]) {
                    "byr", "iyr", "eyr" -> {
                        if (!validyears(jone[0], jone[1].toInt())) continue@loop
                    }
                    "hgt" -> {
                        if (!validdhieght(jone[1])) continue@loop
                    }
                    "hcl" -> {
                        if (!validhaircolor(jone[1])) continue@loop
                    }
                    "ecl" -> {
                        val eyecolor = arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
                        if (!eyecolor.contains(jone[1])) continue@loop
                    }
                    "pid" -> {
                        if (!validpid(jone[1])) continue@loop
                    }
                    "cid" -> if (i.split(" ").size == 7) continue@loop
                }
            } else continue@loop
        }
        count++
    }
    println(count)
}


