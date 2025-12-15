import peekUp

fun main() {
    fun part1(input: List<String>): Int {
        val chars = input.toList().map { it.toMutableList() }

        var sol = 0
        chars.forEachIndexed { i, line ->
            line.forEachIndexed { j, el ->
                if (chars.peekUp(i, j) == '|' || chars.peekUp(i, j) == 'S') {
                    if (chars[i][j] == '^') {
                        if (chars[i][j - 1] != '#') chars[i][j - 1] = '|'
                        if (chars[i][j + 1] != '#') chars[i][j + 1] = '|'
                        sol++
                    } else {
                        chars[i][j] = '|'
                    }
                }
            }
        }
        return sol
    }

    fun part2(input: List<String>): Long {
        val chars = input.map { it.map { it } }.toList()
        val ints = List(chars.size) { MutableList(chars[0].size) { 0L } }

        chars.forEachIndexed { i, line ->
            line.forEachIndexed { j, el ->
                if (chars.peekUp(i, j) == 'S') ints[i][j] = 1
                if ((ints.peekUp(i, j) ?: 0) > 0L) {
                    if (chars[i][j] == '^') {
                        if (chars[i][j - 1] != '#') ints[i][j - 1] += ints[i - 1][j]
                        if (chars[i][j + 1] != '#') ints[i][j + 1] += ints[i - 1][j]
                    } else {
                        ints[i][j] += ints[i - 1][j]
                    }
                }
            }
        }

        return ints.last().sum()
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 21)

    val input = readInput("Day07")
    part1(input).println()

    check(part2(testInput) == 40L)
    part2(input).println()
}

fun <T> List<List<T>>.peekUp(i: Int, j: Int): T? = getOrNull(i - 1)?.getOrNull(j)