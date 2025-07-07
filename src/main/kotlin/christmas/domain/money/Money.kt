package christmas.domain.money

import java.math.BigDecimal

data class Money(
    val amount: BigDecimal,
    val currency: Currency
)
