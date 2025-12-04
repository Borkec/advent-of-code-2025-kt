fun main() {
    fun part1(input: List<String>): ULong {
        val parsed = input[0].split(",").map {
            val ranges = it.split("-")
            ranges[0].toULong() to ranges[1].toULong()
        }
        return parsed.sumOf { (from, to) ->
            (from..to).filter {
                it.toString().isPeriodicHalf()
            }.sum()
        }
    }

    fun part2(input: List<String>): ULong {
        val parsed = input[0].split(",").map {
            val ranges = it.split("-")
            ranges[0].toULong() to ranges[1].toULong()
        }
        return parsed.sumOf { (from, to) ->
            (from..to).filter {
                it.toString().isPeriodic()
            }.sum()
        }
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 1227775554UL)

    val input = readInput("Day02")
    part1(input).println()

    check(part2(testInput) == 4174379265UL)
    part2(input).println()
}


private fun String.isPeriodic(): Boolean {
    val half = length / 2
    return (1..half).any {
        length % it == 0 && chunked(it).distinct().size == 1
    }
}

private fun String.isPeriodicHalf(): Boolean {
    if(length % 2 != 0) return false
    val half = length / 2
    return (take(half) == takeLast(half))
}
