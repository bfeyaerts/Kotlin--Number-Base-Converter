fun main() {
    val a = readLine()!!.toBigInteger()
    val b = readLine()!!.toBigInteger()
    val lcm = a * b / a.gcd(b)
    println(lcm)
}