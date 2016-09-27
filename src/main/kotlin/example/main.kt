package example

import kotlin.browser.document

fun main(args: Array<String>) {
    val el = document.createElement("div")
    el.appendChild(document.createTextNode("Hello!"))
    document.body!!.appendChild(el)

    val counterDiv = document.createElement("div")
    val counterText = document.createTextNode("Counter!")
    counterDiv.appendChild(counterText)
    document.body!!.appendChild(counterDiv)

    println("Hello from KotlinJS!")
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
}
