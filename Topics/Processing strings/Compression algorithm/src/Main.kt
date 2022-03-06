fun main() {
    val input = readLine()!!
    var output = ""

    var char = 'i'
    var count = 0

    for (c in input) {
        if (count == 0) {
            char = c
            count++
        } else if (c == char) {
            count++
        } else {
            output += "$char$count"
            char = c
            count = 1
        }
    }
    output += "$char$count"
    println(output)
}