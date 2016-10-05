package hexogen

import hexogen.algorithms.Kruskal
import hexogen.collections.shuffled
import hexogen.model.Door
import hexogen.model.Point
import hexogen.model.Tile
import hexogen.model.buildWorld
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document
import kotlin.browser.window

val TILE_R = 10.0
val TILE_H = TILE_R * Math.sqrt(3.0) / 2
val TILE_MARGIN = (TILE_R - TILE_H) * 2

val TILE_COLOR = "#fff"
val DOOR_COLOR = "#fff"

fun Tile.center(): Point {
    val rx = x * TILE_H * 2 + TILE_R
    val ry = y * TILE_R * 2 + TILE_H + (x % 2) * TILE_R
    return Point(rx + TILE_MARGIN, ry + TILE_MARGIN)
}

fun worldSize(width: Int, height: Int): Point {
    val rx = width * TILE_H * 2 + TILE_R * 2
    val ry = height * TILE_R * 2 + TILE_H * 2 + TILE_R
    return Point(rx + TILE_MARGIN * 2, ry + TILE_MARGIN * 2)
}

fun Tile.paint(context: CanvasRenderingContext2D) {
    val (x, y) = center()
    val r = TILE_R
    val h = TILE_H
    context.fillStyle = TILE_COLOR
    context.beginPath()
    context.moveTo(x + r, y)
    context.lineTo(x + r / 2, y + h)
    context.lineTo(x - r / 2, y + h)
    context.lineTo(x - r, y)
    context.lineTo(x - r / 2, y - h)
    context.lineTo(x + r / 2, y - h)
    context.closePath()
    context.fill()
}

fun Door.paint(context: CanvasRenderingContext2D) {
    val (sx, sy) = A.center()
    val (ex, ey) = B.center()
    context.strokeStyle = DOOR_COLOR
    context.lineWidth = TILE_R
    context.beginPath()
    context.moveTo(sx, sy)
    context.lineTo(ex, ey)
    context.closePath()
    context.stroke()
    A.paint(context)
    B.paint(context)
}


fun main(args: Array<String>) {
    val canvas = document.getElementById("main") as HTMLCanvasElement
    val context = canvas.getContext("2d") as CanvasRenderingContext2D

    val size = worldSize(100, 50)

    canvas.width = size.x.toInt()
    canvas.height = size.y.toInt()

    context.fillStyle = "#000"
    context.fillRect(0.0, 0.0, size.x, size.y)

    val algorithm = Kruskal(buildWorld(100, 50).shuffled())

    fun animate() {
        algorithm.next()?.apply {
            paint(context)
            window.setTimeout({ animate() }, 0)
        }
    }

    animate()
}

// http://www.redblobgames.com/grids/hexagons/
// http://devmag.org.za/2013/08/31/geometry-with-hex-coordinates/
