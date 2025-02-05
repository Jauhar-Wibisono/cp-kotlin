// source: https://codeforces.com/contest/2008/submission/279090386
const val mod = 998244353
fun Int.modClip(): Int { if (this >= mod) { return this - mod } else if (this < 0) { return this + mod }; return this }
infix fun Int.modMul(b: Int): Int { return ((this.toLong() * b) % mod).toInt() }
infix fun Int.modAdd(b: Int): Int { val ans = this + b; return if (ans >= mod) ans - mod else ans }
infix fun Int.modSub(b: Int): Int { val ans = this - b; return if (ans < 0) ans + mod else ans }
fun Long.toModInt(): Int = (this % mod).toInt().modClip()
fun Int.modInv(): Int = modPow(this, mod-2)
fun Int.modNeg(): Int = if (this == 0) 0 else mod - this
infix fun Int.modDiv(b: Int): Int { return this modMul b.modInv() }
infix fun Int.modPow(e: Int): Int {
    var x = this; var e = e ; var ret = 1
    while (e > 0) {
        if(e and 1 == 1) ret = ret modMul x
        x = x modMul x
        e = e shr 1
    }
    return ret
}
