fun main() {
	data class Point(val x: Int, val y: Int, val type: Char)

	fun getAdjacentPoints(points: List<List<Point>>, row: Int, col: Int): Set<Point> {        /*
		 -------------------
		 |     |     |     |
		 |-1,-1|-1,0 |-1,1 |
		 |     |     |     |
		 -------------------
		 |     |     |     |
		 | 0,-1| 0,0 | 0,1 |
		 |     |     |     |
		 -------------------
		 |     |     |     |
		 | 1,-1| 1,0 | 1,1 |
		 |     |     |     |
		 -------------------
		 */

		return listOf(Pair(-1, -1), Pair(-1, 1), Pair(1, -1), Pair(1, 1), Pair(1, 0), Pair(0, 1), Pair(0, -1), Pair(-1, 0)).mapNotNull {
			if ((row + it.first) in points.indices && (col + it.second) in points[row].indices) {
				points[row + it.first][col + it.second]
			}
			else {
				null
			}
		}.toSet()
	}

	fun printMap(points: List<List<Point>>) {
		println("=".repeat(points.first().size))

		for (line in points) {
			println(line.map { it.type }.joinToString(""))
		}
	}

	fun getPoints(input: List<String>): MutableList<MutableList<Point>> = input.mapIndexed { y, line ->
		line.mapIndexed { x, char -> Point(x, y, char) }.toMutableList()
	}.toMutableList()

	fun processPoints(points: MutableList<MutableList<Point>>): Int {
		var count = 0

		points.forEachIndexed { row, line ->
			line.forEachIndexed { col, point ->
				if (point.type == '@') {
					getAdjacentPoints(points, row, col).count {
						it.type == '@'
					}.let {
						if (it < 4) {
							count += 1
						}
					}
				}
			}
		}

		return count
	}

	fun part1(input: List<String>): Int {
		val points = getPoints(input)
		return processPoints(points)
	}

	fun part2(input: List<String>): Int {
		val points = getPoints(input)
		var count = 0
		var removed = 0

		top@ do {
			for ((row, line) in points.withIndex()) {
				for ((col, point) in line.withIndex()) {
					if (point.type == '@') {
						getAdjacentPoints(points, row, col).count {
							it.type == '@'
						}.let {
							if (it < 4) {
								count = 1
								points[row][col] = point.copy(type = '.')
								removed += 1

								// printMap(points)

								continue@top
							}
							else {
								count = 0
							}
						}
					}
				}
			}
		} while (count > 0)

		return removed
	}

	val testInput = readInput("Day04_test")
	check(part1(testInput) == 13)
	check(part2(testInput) == 43)

	val input = readInput("Day04")
	part1(input).println()
	part2(input).println()
}
