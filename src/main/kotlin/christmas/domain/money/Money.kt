package christmas.domain.money

import java.math.BigDecimal

data class Money(
    val amount: BigDecimal,
    val currency: Currency
) {
    fun multiply(multiplicand: Int): Money {
        return Money(
            amount.multiply(BigDecimal.valueOf(multiplicand.toLong())),
            currency,
        )
    }
}
