package hexogen.collections

class DisjointSet<T> {
    private val map = mutableMapOf<T, Tag>()

    fun test(x: T, y: T): Boolean {
        if (x == y) return true
        val xtag = map[x]
        val ytag = map[y]
        return xtag != null && ytag != null && find(xtag) == find(ytag)
    }

    fun merge(x: T, y: T) {
        val xtag = map.getOrPut(x) { Tag() }
        val ytag = map.getOrPut(y) { Tag() }
        merge(xtag, ytag)
    }
}

private class Tag() {
    var parent = this
    var height = 0
}

private fun find(tag: Tag): Tag {
    if (tag.parent != tag)
        tag.parent = find(tag.parent)
    return tag.parent
}

private fun merge(x: Tag, y: Tag) {
    val xroot = find(x)
    val yroot = find(y)

    if (xroot == yroot)
        return

    if (xroot.height < yroot.height) {
        xroot.parent = yroot
    } else {
        yroot.parent = xroot
        if (xroot.height == yroot.height)
            xroot.height++
    }
}
