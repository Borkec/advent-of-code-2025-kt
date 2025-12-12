import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        val ranges = input.takeWhile { it != "" }.map { it.split("-").let { it[0].toLong() to it[1].toLong() } }
        val queries = input.takeLastWhile { it != "" }.reversed().map { it.toLong() }

        return queries.count { query ->
            ranges.any { range -> range.first <= query && query <= range.second }
        }
    }

    fun part2(input: List<String>): Long {
        val ranges = input.takeWhile { it != "" }.map { it.split("-").let { it[0].toLong() to it[1].toLong() } }.sortedBy { it.first }

        var (start, end) = ranges.first()
        var sum = 0L
        for(range in ranges.drop(1)) {
            if(range.first <= end) {
                end = max(end, range.second)
            } else {
                sum += end-start+1
                start = range.first
                end = range.second
            }
        }
        sum += end-start+1
        return sum
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 3)

    val input = readInput("Day05")
    part1(input).println()

    check(part2(testInput) == 14L)
    part2(input).println()
}
