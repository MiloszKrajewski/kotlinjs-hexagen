package hexogen.algorithms

import hexogen.collections.DisjointSet

interface Edge<N> {
    val A: N
    val B: N
}

class Kruskal<N, E : Edge<N>>(edges: Sequence<E>) {
    val iterator = edges.iterator()
    val sets = DisjointSet<N>()

    fun next(): E? {
        if (!iterator.hasNext())
            return null

        val edge = iterator.next()
        if (sets.test(edge.A, edge.B))
            return next()

        sets.merge(edge.A, edge.B)
        return edge
    }
}
