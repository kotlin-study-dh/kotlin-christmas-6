package christmas.domain.money

import java.math.BigDecimal

data class Money(
    val amount: BigDecimal,
    val currency: Currency
) {
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
}
