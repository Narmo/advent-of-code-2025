fun main() {
	fun part1(input: List<String>): Int {
		return input.sumOf { bank ->
			val values = mutableListOf<Int>()

			for (i in 0..<bank.count() - 1) {
				for (j in (i + 1)..<bank.count()) {
					values.add(bank[i].digitToInt() * 10 + bank[j].digitToInt())
				}
			}

			values.max()
		}
	}

	fun part2(input: List<String>): Long {
		TODO()
	}

	val testInput = readInput("Day03_test")
	check(part1(testInput) == 357)
	// check(part2(testInput) == 3121910778619L)

	val input = readInput("Day03")
	part1(input).println()
	// part2(input).println()
}
