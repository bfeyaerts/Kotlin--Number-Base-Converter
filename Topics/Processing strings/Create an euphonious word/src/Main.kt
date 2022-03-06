const val VOWELS = "aeiouy"
fun main() {
    val word = readLine()!!

    var count = 0
    var vowels = false
    var charsNeeded = 0

    for (c in word) {
        val isVowel = c in VOWELS
        if (count == 0) {
            count++
            vowels = isVowel
        } else if (vowels == isVowel) {
            count++
        } else {
            count = 1
            vowels = isVowel
        }
        if (count == 3) {
            charsNeeded++
            count = 1
        }
    }

    println(charsNeeded)
}