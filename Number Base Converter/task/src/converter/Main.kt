package converter

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

fun main() {
    do {
        println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
        val response1 = readLine()!!

        if (response1 == "/exit")
            return

        val (sourceBase, targetBase) = response1.split(" ").map { it.toInt() }
        do {
            println("Enter number in base $sourceBase to convert to base $targetBase (To go back type /back)")

            val response2 = readLine()!!.lowercase()
            if (response2 == "/back")
                break

            val base10Source = convertToBase10(response2, sourceBase)
            val target = convert(base10Source, targetBase)
            println("Conversion result: $target")
        } while (true)
    } while (true)
}

fun convert(sourceNumber: BigDecimal, targetBase: Int): String {
    val divisor = targetBase.toBigDecimal()
    var integerPart = sourceNumber.setScale(0, RoundingMode.DOWN)
    var fractionalPart = sourceNumber - integerPart
    var backwardConvertedIntegerPart = ""
    while (integerPart > BigDecimal.ZERO) {
        val (quotient, remainder) = integerPart.divideAndRemainder(divisor)
        backwardConvertedIntegerPart += toDigit(remainder.toInt())
        integerPart = quotient
    }

    return if (sourceNumber.scale() > 0) {
        var convertedFractionalPart = ""
        while (fractionalPart > BigDecimal.ZERO && convertedFractionalPart.length < 5) {
            fractionalPart *= divisor
            val intPart = fractionalPart.setScale(0, RoundingMode.DOWN)
            fractionalPart -= intPart
            convertedFractionalPart += toDigit(intPart.toInt())
        }
        "${backwardConvertedIntegerPart.reversed()}.${convertedFractionalPart.padEnd(5, '0')}"
    } else {
        backwardConvertedIntegerPart.reversed()
    }
}

fun toDigit(digit : Int) : Char {
    return if (digit >= 10) {
        'a' + (digit - 10)
    } else {
        '0' + digit
    }
}

fun toDigit(digit : Char) : Int {
    return if (digit.isDigit()) {
        digit.digitToInt()
    } else {
        digit - 'a' + 10
    }
}

fun convertToBase10 (sourceNumber: String, sourceBase: Int) : BigDecimal {
    if (sourceNumber.contains('.')) {
        val (integerPart, fractionalPart) = sourceNumber.split('.')
        val scale = 5.coerceAtLeast(fractionalPart.length)

        var base10Number = convertToBase10(integerPart, sourceBase)
        var divisor = sourceBase.toBigDecimal()
        for (c in fractionalPart) {
            val digit = toDigit(c)
            base10Number += digit.toBigDecimal()
                .setScale(scale, RoundingMode.UNNECESSARY)
                .divide(divisor, RoundingMode.DOWN)
            divisor *= sourceBase.toBigDecimal()
        }

        return base10Number
    } else {
        return BigInteger(sourceNumber, sourceBase).toBigDecimal()
    }
}