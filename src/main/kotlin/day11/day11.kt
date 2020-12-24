package day11

import java.io.File


/*
fun part(file: Array<CharArray>): Array<CharArray> {
    val result = Array(file.size) { CharArray(file[0].size) }
    for (f in 0..file.lastIndex) {
        for (i in 0..file[f].lastIndex) {
            if (file[f][i] == 'L') {
                val temp = file.filterIndexed { index, _ -> index in f - 1..f + 1 }
                    .flatMap { it.filterIndexed { index, _ -> index in i - 1..i + 1 } } - file[f][i]
                if (temp.count { it == '#' } > 0) result[f][i] = 'L' else result[f][i] = '#'

            } else if (file[f][i] == '#') {
                val temp = file.filterIndexed { index, _ -> index in f - 1..f + 1 }
                    .flatMap { it.filterIndexed { index, _ -> index in i - 1..i + 1 } } - file[f][i]
                if (temp.count { it == '#' } > 3) result[f][i] = 'L' else result[f][i] = '#'
            } else {
                result[f][i] = '.'
            }
        }
    }
    return if (file.contentDeepEquals(result)) result else part(result)
}
*/


fun part1(file: Array<CharArray>): Array<CharArray> {

    val result = Array(file.size) { CharArray(file[0].size) }
    for (f in 0..file.lastIndex) {
        for (i in 0..file[f].lastIndex) {
            if (file[f][i] == 'L') {
                val temp = CharArray(8)
                runCatching { file[f - 1][i - 1] }.onSuccess { temp[0] = it }.onFailure { temp[0] = ' ' }
                runCatching { file[f - 1][i] }.onSuccess { temp[1] = it }.onFailure { temp[1] = ' ' }
                runCatching { file[f - 1][i + 1] }.onSuccess { temp[2] = it }.onFailure { temp[2] = ' ' }
                runCatching { file[f][i - 1] }.onSuccess { temp[3] = it }.onFailure { temp[3] = ' ' }
                runCatching { file[f][i + 1] }.onSuccess { temp[4] = it }.onFailure { temp[4] = ' ' }
                runCatching { file[f + 1][i - 1] }.onSuccess { temp[5] = it }.onFailure { temp[5] = ' ' }
                runCatching { file[f + 1][i] }.onSuccess { temp[6] = it }.onFailure { temp[6] = ' ' }
                runCatching { file[f + 1][i + 1] }.onSuccess { temp[7] = it }.onFailure { temp[7] = ' ' }
                if (temp.count { it == '#' } > 0) result[f][i] = 'L' else result[f][i] = '#'
            } else if (file[f][i] == '#') {
                val temp = CharArray(8)
                runCatching { file[f - 1][i - 1] }.onSuccess { temp[0] = it }.onFailure { temp[0] = ' ' }
                runCatching { file[f - 1][i] }.onSuccess { temp[1] = it }.onFailure { temp[1] = ' ' }
                runCatching { file[f - 1][i + 1] }.onSuccess { temp[2] = it }.onFailure { temp[2] = ' ' }
                runCatching { file[f][i - 1] }.onSuccess { temp[3] = it }.onFailure { temp[3] = ' ' }
                runCatching { file[f][i + 1] }.onSuccess { temp[4] = it }.onFailure { temp[4] = ' ' }
                runCatching { file[f + 1][i - 1] }.onSuccess { temp[5] = it }.onFailure { temp[5] = ' ' }
                runCatching { file[f + 1][i] }.onSuccess { temp[6] = it }.onFailure { temp[6] = ' ' }
                runCatching { file[f + 1][i + 1] }.onSuccess { temp[7] = it }.onFailure { temp[7] = ' ' }
                if (temp.count { it == '#' } > 3) result[f][i] = 'L' else result[f][i] = '#'
            } else {
                result[f][i] = '.'
            }
        }
    }
    return if (file.contentDeepEquals(result)) result else part1(result)
}

fun main() {
    val input = File("src/main/kotlin/day11/input.txt").readLines().map { it.toCharArray() }.toTypedArray()
    println(part1(input).sumBy { i -> i.count { it == '#' } })
}