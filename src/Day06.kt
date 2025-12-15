fun main() {
    fun part1(input: List<String>): Long {
        val inputTransposed = input.map {
            it.trim().split(regex = Regex("[ \\t\\r\\n]+"))
        }.transposed()

        return inputTransposed.sumOf { line ->
            val numbers = line.dropLast(1).map { it.toLong() }
            when (line.last()) {
                "*" -> numbers.reduce { acc, i -> acc * i }
                "+" -> numbers.sum()
                else -> 0
            }
        }
    }

    fun part2(input: List<String>): Long {
        val inputTransposed = input
            .map { it.toCharArray().toList() }
            .transposedChar()

        var idx = 0
        var sol = 0L
        while (idx < inputTransposed.size) {
            val op = inputTransposed[idx].last()
            var acc = if(op == '*') 1L else 0L
            while (idx < inputTransposed.size && inputTransposed[idx].all { it.isWhitespace() }.not()) {
                val value = inputTransposed[idx].dropLast(1).filterNot { it.isWhitespace() }.joinToString(separator = "").toLong()
                when(op) {
                    '*' -> acc *= value
                    '+' -> acc += value
                }
                idx++
            }
            sol += acc
            idx++
        }
        return sol
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 4277556L)

    val input = readInput("Day06")
    part1(input).println()

    check(part2(testInput) == 3263827L)
    part2(input).println()
}

fun <T> List<List<T>>.transposed(): List<List<T>> {
    if (isEmpty()) return emptyList()

    val maxCols = maxOf { it.size }

    return (0 until maxCols).map { col ->
        map { row -> row[col] }
    }
}

fun List<List<Char>>.transposedChar(): List<List<Char>> {
    if (isEmpty()) return emptyList()

    val maxCols = maxOf { it.size }

    return (0 until maxCols).map { col ->
        map { row -> row.getOrNull(col) ?: ' ' }
    }
}

