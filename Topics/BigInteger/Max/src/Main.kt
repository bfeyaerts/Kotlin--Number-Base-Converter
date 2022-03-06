import java.math.BigInteger

fun main() {
    val a = readLine()!!.toBigInteger()
    val b = readLine()!!.toBigInteger()
    println((a + b + (a - b).abs()) / BigInteger.valueOf(2))
}