package christmas.configuration.promotion

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
import christmas.domain.product.Product
import java.math.BigDecimal

class ChristmasDdayDiscount : Promotion {
    companion object {
        private const val START_DAY = 1
        private const val END_DAY = 25

        private val DISCOUNT_MONEY_FIRST_DAY = Money.longValueOf(1000, Currency.KRW)
        private val DISCOUNT_INCREMENT_PER_DAY = Money.longValueOf(100, Currency.KRW)
    }

    override fun discountAmount(orders: Orders): Money {
        val dayOfMonth = orders.date.dayOfMonth
        val incrementAmount = dayOfMonth - 1

        return when (dayOfMonth) {
            in START_DAY..END_DAY -> {
                DISCOUNT_MONEY_FIRST_DAY.add(
                    DISCOUNT_INCREMENT_PER_DAY.multiply(incrementAmount)
                )
            }

            else -> {
                Money(BigDecimal.ZERO, orders.currency())
            }
        }
    }

    override fun giveawayProduct(orders: Orders): Product? {
        return null
    }
}
