fun main() {
	fun part1(input: List<String>): Long {
		var sum = 0L
		val ranges = input.first().split(",").map { it.split("-").map { num -> num.toLong() } }

		for (range in ranges) {
			for (num in range[0]..range[1]) {
				val stringRepr = num.toString()

				if (stringRepr.count() % 2 != 0) {
					continue
				}

				stringRepr.chunked(stringRepr.count() / 2).let {
					if (it[0] == it[1]) {
						sum += num
					}
				}
			}
		}

		return sum
	}

	fun part2(input: List<String>): Long {
		var sum = 0L
		val ranges = input.first().split(",").map { it.split("-").map { num -> num.toLong() } }

		for (range in ranges) {
			for (num in range[0]..range[1]) {
				val stringRepr = num.toString()

				for (i in 1 until stringRepr.count()) {
					stringRepr.chunked(i).all {
						if (it.count() != i) return@all false
						it == stringRepr.take(i)
					}.let { allMatch ->
						if (allMatch) {
							sum += num
							break
						}
					}
				}
			}
		}

		return sum
	}

	val testInput = readInput("Day02_test")
	check(part1(testInput) == 1227775554L)
	check(part2(testInput) == 4174379265L)

	val input = readInput("Day02")
	part1(input).println()
	part2(input).println()
}
