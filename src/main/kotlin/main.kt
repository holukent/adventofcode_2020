fun main() {

    val numbers = listOf(5, 2, 10, 4)

    val sum = numbers.reduce { sum, element -> sum + element } * 2
    println(sum)
    val sumDoubled = numbers.fold(6) { sum, element -> sum + element * 2 }
    println(sumDoubled)

    val sumDoubledReduce =
        numbers.reduce { sum, element -> sum + element * 2 } //incorrect: the first element isn't doubled in the result
    println(sumDoubledReduce)
}

