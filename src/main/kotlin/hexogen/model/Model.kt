package hexogen.model

import hexogen.algorithms.Edge

data class Tile(val x: Int, val y: Int)
data class Door(override val A: Tile, override val B: Tile) : Edge<Tile>
data class Point(val x: Double, val y: Double)

fun buildWorld(width: Int, height: Int): Sequence<Door> {
    val result = mutableListOf<Door>()
    val map = mutableMapOf<Int, Tile>()

    fun encode(x: Int, y: Int) = y * (width + 1) + x
    fun tileAt(x: Int, y: Int) = map.getOrPut(encode(x, y)) { Tile(x, y) }

    for (y in 0..height) {
        for (x in 0..width) {
            val tile = tileAt(x, y)
            if (x > 0) result.add(Door(tile, tileAt(x - 1, y)))
            if (y > 0) result.add(Door(tile, tileAt(x, y - 1)))
            if (x < width && y > 0 && x % 2 == 0) result.add(Door(tile, tileAt(x + 1, y - 1)))
            if (x > 0 && y > 0 && x % 2 == 0) result.add(Door(tile, tileAt(x - 1, y - 1)))
        }
    }

    return result.asSequence()
}
