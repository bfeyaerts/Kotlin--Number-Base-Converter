fun main() {
    val number = readLine()!!
    var firstHalf = 0
    for (i in 0 until number.length / 2) {
        firstHalf += number[i].digitToInt()
    }
    var secondHalf = 0
    for (i in number.length / 2 until number.length) {
        secondHalf += number[i].digitToInt()
    }
    println(if (firstHalf == secondHalf) "YES" else "NO")
}