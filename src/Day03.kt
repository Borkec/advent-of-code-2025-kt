fun main() {
    fun part1(input: List<String>): Int {

        return input.sumOf { batteries ->
            println(batteries)
            val first = batteries
                .mapIndexed { idx, dig -> idx to dig.digitToInt() }
                .dropLast(1)
                .maxBy { it.second }

            val second = batteries.drop(first.first+1).maxBy { it.digitToInt() }
            "${first.second}$second".toInt()
        }
    }

    fun part2(input: List<String>): Long {
        return input.sumOf { batteries ->
            batteries.findMaxFromIndex(12).toLong()
        }
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 357)

    val input = readInput("Day03")
    part1(input).println()

    check(part2(testInput) == 3121910778619L)
    part2(input).println()
}

fun String.findMaxFromIndex(left: Int): String {
    if(length == left) return this
    val found = substring(0, length-left+1).mapIndexed { idx, num -> idx to num }.maxBy { it.second.digitToInt() }
    return found.second + if(left > 1) substring(found.first+1).findMaxFromIndex(left-1) else ""
}
