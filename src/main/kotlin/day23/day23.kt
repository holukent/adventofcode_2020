import java.io.File

data class LLNode(val value: Int, var next: LLNode?)

fun main() {
    val cups = File("src/main/kotlin/day23/input").readText().split("").mapNotNull { if (it == "") null else LLNode(it.toInt(), null) }

    for (i in cups.indices) {
        cups[i].next = cups[(i+1)%cups.size]
    }

    var curr: LLNode = cups[8]
    for (i in 10..1000000) {
        curr.next = LLNode(i, null)
        curr = curr.next!!
    }
    curr.next = cups[0]
    curr = curr.next!!
    val cup_map = mutableMapOf<Int, LLNode>(curr.value to curr)
    var cc = curr.next!!
    while (cc != curr) {
        cup_map[cc.value] = cc
        cc = cc.next!!
    }
    for (i in 1..10000000) {
        val move = curr.next
        curr.next = curr.next?.next?.next?.next

        var destination = if (curr.value == 1) 1000000 else curr.value - 1
        while (destination in listOf(move?.value, move?.next?.value, move?.next?.next?.value)) {
            destination -= 1
            if (destination < 1) {
                destination = 1000000
            }
        }

        val dest = cup_map[destination]

        move?.next?.next?.next = dest!!.next
        dest.next = move
        curr = curr.next!!
    }

    val one = cup_map[1]!!
    println("${one.next!!.value.toLong() * one.next!!.next!!.value.toLong()}")
}