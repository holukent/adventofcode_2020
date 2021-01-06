package day25

import java.io.File

fun main() {
    val input = File("src/main/kotlin/day25/input.txt").readLines()
    val card = input[0].toInt()
    var loop = 0
    val subjectnumber = 7
    var result = 1

    while (result != card) {
        loop++
        result = (subjectnumber * result) % 20201227
    }
    val door = input[1].toInt()
    var encryptionkey = 1L
    repeat(loop) {
        encryptionkey = (door * encryptionkey) % 20201227
    }
    println(encryptionkey)

}