package christmas.domain.money

import java.math.BigDecimal

data class Money(
    val amount: BigDecimal,
    val currency: Currency
) {
    companion object {
        fun intValueOf(amount: Int, currency: Currency): Money {
            return Money(BigDecimal(amount), currency)
        }

        fun doubleValueOf(amount: Double, currency: Currency): Money {
            return Money(BigDecimal(amount), currency)
        }
    }

    fun multiply(multiplicand: Int): Money {
        return Money(
            amount.multiply(BigDecimal.valueOf(multiplicand.toLong())),
            currency,
        )
    }
}
