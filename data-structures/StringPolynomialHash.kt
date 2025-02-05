class StringPolynomialSingleHash(val s: String) {
	companion object {
		private const val maxn = 300000
		private const val mod = 1000000007
		infix fun Int.modMul(b: Int): Int { return ((this.toLong() * b) % mod).toInt() }
		infix fun Int.modAdd(b: Int): Int { val ans = this + b; return if (ans >= mod) ans - mod else ans }
		infix fun Int.modSub(b: Int): Int { val ans = this - b; return if (ans < 0) ans + mod else ans }
		fun Int.modInv(): Int = this.modPow(mod-2)
		infix fun Int.modPow(e: Int): Int {
		    var x = this; var e = e ; var ret = 1
		    while (e > 0) {
		        if(e and 1 == 1) ret = ret modMul x
		        x = x modMul x
		        e = e shr 1
		    }
		    return ret
		}

		private val base = 98237
		private val ibase = base.modInv()
		val pw = generateSequence(1) { it modMul base }.take(maxn).toList()
		val ipw = generateSequence(1) { it modMul ibase }.take(maxn).toList()
	}

	private val n = s.length
	private val p = pw.take(n).zip(s.toList()) { p, c -> p modMul c.code }.runningFold(0) { sum, cur -> sum modAdd cur }
	
	// Returns hash of s[l..r], where l and r are one-index-based
	// O(1)
	fun get(l: Int, r: Int): Int {
		return (p[r] modSub p[l-1]) modMul ipw[l]
	}
}

fun main() {
	val s1 = "aabbaa"
	val s2 = "bbaabb"

	val h1 = StringPolynomialSingleHash(s1)
	val h2 = StringPolynomialSingleHash(s2)

	assert(h1.get(1, 1) == h2.get(3, 3)) // a
	assert(h1.get(1, 4) == h2.get(3, 6)) // aabb
	assert(h1.get(3, 6) == h2.get(1, 4)) // bbaa

	assert(h1.get(1, 1) != h2.get(1, 1)) // a != b
	assert(h1.get(1, 6) != h2.get(1, 6)) // aabbaa != bbaabb
	assert(h1.get(1, 2) != h2.get(3, 3)) // aa != a
}
