fun main() {
    val (string, n) = readLine()!!.split(" ")
    val offset = n.toInt()

    if (string.length < 2 || offset >= string.length) {
        println(string)
    } else {
        println("${string.drop(offset)}${string.take(offset)}")
    }
}