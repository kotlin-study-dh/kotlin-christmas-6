package christmas.domain.money

import java.math.BigDecimal

data class Money(
    val amount: BigDecimal,
    val currency: Currency
) : Comparable<Money> {
    companion object {
        fun longValueOf(amount: Long, currency: Currency): Money {
            return Money(BigDecimal.valueOf(amount), currency)
        }

        fun doubleValueOf(amount: Double, currency: Currency): Money {
            return Money(BigDecimal.valueOf(amount).setScale(2), currency)
        }
    }

    fun multiply(multiplicand: Int): Money {
        return Money(
            amount.multiply(BigDecimal.valueOf(multiplicand.toLong())),
            currency,
        )
    }

    fun add(target: Money): Money {
        val resultAmount = amount.add(target.amount)
        return Money(resultAmount, currency)
    }

    fun subtract(target: Money): Money {
        val resultAmount = amount.subtract(target.amount)
        require(resultAmount >= BigDecimal(0)) { "Money cannot have negative value." }
        return Money(resultAmount, currency)
    }

    fun isZero(): Boolean {
        return amount.compareTo(BigDecimal.valueOf(0L)) == 0
    }

    override fun compareTo(other: Money): Int {
        return amount.compareTo(other.amount)
    }
}
