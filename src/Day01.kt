fun main() {
    fun part1(input: List<String>): Int {
        var dial = 50

        return input.count {
            val dir = it.first()
            val amt = it.drop(1).toInt()

            when (dir) {
                'L' -> dial -= amt
                'R' -> dial += amt
            }
            dial = dial.mod(100)
            dial == 0
        }
    }

    fun part2(input: List<String>): Int {
        var dial = 50

        return input.sumOf {
            val dir = it.first()
            val amt = it.drop(1).toInt()
            val fulls = amt / 100
            val dialOld = dial

            when (dir) {
                'L' -> dial -= amt % 100
                'R' -> dial += amt % 100
            }
            val dialMod = (dial+100) % 100
            val addOne = if(dialMod == 0 || (dial != dialMod && dialOld != 0)) 1 else 0
            (fulls + addOne).also { dial = dialMod }
        }
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)

    val input = readInput("Day01")
    part1(input).println()

    check(part2(testInput) == 6)
    part2(input).println()
}
