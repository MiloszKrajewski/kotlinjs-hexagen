package hexogen.collections

fun <T> MutableList<T>.swap(x: Int, y: Int) {
    val t = this[x]
    this[x] = this[y]
    this[y] = t
}

fun <T> MutableList<T>.shuffle() {
    fun random(range: Int) = Math.floor((Math.random() * range) + 1.0)
    for (i in size - 1 downTo 0) swap(i, random(i))
}

fun <T> Sequence<T>.shuffled(): Sequence<T> =
        this.toMutableList().apply { shuffle() }.asSequence()
