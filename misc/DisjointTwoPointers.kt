class DisjointTwoPointers<T> {
	fun go(a: List<T>, comp: (e1: T, e2: T) -> Boolean, action: (e: T) -> Unit) {
		var l = 0
        while (l < a.count()) {
            var r = l
            while (r < a.count() && comp(a[l], a[r])) r++
            action(a[l])
            l = r
        }
	}
}

fun main() {
	val s = "abbcccdddd"
	DisjointTwoPointers<Char>().go(s.toList(), { e1, e2 -> e1 == e2 }, { e -> print(e) })
}
