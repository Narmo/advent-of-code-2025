fun main() {
	fun part1(input: List<String>): Int {
		var password = 0
		var position = 50

		for (line in input) {
			val direction = line.take(1)
			val distance = line.substring(1).toInt()

			if (direction == "L") {
				position -= distance
			}
			else if (direction == "R") {
				position += distance
			}

			position %= 100

			if (position < 0) {
				position += 100
			}

			if (position == 0) {
				password += 1
			}
		}

		return password
	}

	fun part2(input: List<String>): Int {
		var password = 0
		var position = 50

		for (line in input) {
			val direction = line.take(1)
			val distance = line.substring(1).toInt().let { if (direction == "L") -it else it }

			repeat(kotlin.math.abs(distance)) {
				position += if (distance > 0) 1 else -1

				if (position < 0) {
					position = 99
				}

				if (position > 99) {
					position = 0
				}

				if (position == 0) {
					password += 1
				}
			}
		}

		return password
	}

	val testInput = readInput("Day01_test")
	check(part1(testInput) == 3)
	check(part2(testInput) == 6)

	val input = readInput("Day01")
	part1(input).println()
	part2(input).println()
}
