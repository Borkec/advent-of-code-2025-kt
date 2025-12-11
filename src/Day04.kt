fun main() {
    fun part1(input: List<String>): Int {
        return input.indices.sumOf { i ->
            input[i].indices.count { j ->
                input[i][j] == '@' && input.getNeighbors(i, j).count { it.second == '@' } < 4
            }
        }
    }

    fun part2(input: List<String>): Int {

        var oldList = input.toList()
        val newList = oldList.map { it.toMutableList() }

        var sol = 0
        while (true) {
            for (i in oldList.indices) {
                for (j in oldList[i].indices) {
                    if (oldList[i][j] == '@' && oldList.getNeighbors(i, j).count { it.second == '@' } < 4) {
                        newList[i][j] = '.'
                        sol++
                    }
                }
            }
            if (newList.map { it.joinToString("") } == oldList) break
            oldList = newList.map { it.joinToString("") }
        }

        return sol
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)

    val input = readInput("Day04")
    part1(input).println()

    check(part2(testInput) == 43)
    part2(input).println()
}

fun List<String>.getNeighbors(i: Int, j: Int) =
    listOf(
        -1 to -1,
        -1 to 0,
        -1 to 1,
        0 to -1,
        0 to 1,
        1 to -1,
        1 to 0,
        1 to 1
    ).mapNotNull { (it.first + i) to (it.second + j) to getOrNull(it.first + i)?.getOrNull(it.second + j) }