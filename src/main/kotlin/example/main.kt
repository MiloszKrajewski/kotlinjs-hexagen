package example

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.document
import kotlin.dom.onClick

fun drawCell(context: CanvasRenderingContext2D, x: Double, y: Double, r: Double) = {
    val r12 = r*0.5
    val r32 = r*Math.sqrt(3.0) / 2

    context.fillStyle = "#fff"
    context.beginPath()
    context.moveTo(x + r, y)
    context.lineTo(x + r12, y + r32)
    context.lineTo(x - r12, y + r32)
    context.lineTo(x - r, y)
    context.lineTo(x - r12, y - r32)
    context.lineTo(x + r12, y - r32)
    context.closePath()
    context.fill()
}

fun main(args: Array<String>) {
    val width = 400
    val height = 400

    val canvas = document.getElementById("main") as HTMLCanvasElement
    val context = canvas.getContext("2d") as CanvasRenderingContext2D
    canvas.width = width
    canvas.height = height

    context.fillStyle = "#000"
    context.fillRect(0.0, 0.0, width.toDouble(), height.toDouble())

    canvas.onClick {
        drawCell(context, 100.0, 100.0, 50.0)
        println("Clicked111!")
    }

    println("Hello from KotlinJS!")
}
    // http://www.redblobgames.com/grids/hexagons/
    // http://devmag.org.za/2013/08/31/geometry-with-hex-coordinates/

//    context = document.getElementById('main').getContext('2d');
//    context.fillStyle = "#000";
//    context.fillRect(0, 0, 600, 400);
//
//    let rotate = (x, y, a) => [x*Math.cos(a) - y*Math.sin(a), x*Math.sin(a) + y*Math.cos(a)];
//    let torad = (x) => x/360*2*Math.PI;
//
//    let hexagon = (ox, oy, r) => {
//        context.fillStyle = "#fff";
//        context.beginPath();
//        context.moveTo(r + ox, 0 + oy);
//        for (var i = 1; i < 6; i++) {
//        let [x, y] = rotate(r, 0, torad(i*60));
//        context.lineTo(x + ox, y + oy);
//    }
//        context.closePath();
//        context.fill();
//    }
//
//    let path = (sx, sy, ex, ey, r) => {
//        context.strokeStyle = "#fff";
//        context.lineWidth = r;
//        context.beginPath();
//        context.moveTo(sx, sy);
//        context.lineTo(ex, ey);
//        context.closePath();
//        context.stroke();
//        hexagon(sx, sy, r);
//        hexagon(ex, ey, r);
//    };
//
//    let offsetXY = (r) => rotate(r, 0, torad(30));
//    let [ox, oy] = offsetXY(50);
//
//// hexagon(200, 200, 50);
//// hexagon(200+ox*2, 200+oy*2, 50);
//    hexagon(200+ox*2, 200-oy*2, 50);
//    hexagon(200, 200+oy*4, 50);
//    hexagon(200+ox*4, 200, 50);
//
//    path(200, 200, 200+ox*2, 200+oy*2, 50)
