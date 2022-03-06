import java.math.BigDecimal
import java.math.RoundingMode

fun main() {
    val initialAmount = readLine()!!.toBigDecimal()
    val interestRate = readLine()!!.toBigDecimal()
    val years = readLine()!!.toInt()

    val finalAmount = initialAmount * (BigDecimal.ONE + interestRate.setScale(2, RoundingMode.UNNECESSARY) / BigDecimal(100)).pow(years)

    println("Amount of money in the account: ${finalAmount.setScale(2, RoundingMode.CEILING)}")
}