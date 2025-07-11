package christmas.configuration.promotion

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
import christmas.domain.product.Product
import java.time.DayOfWeek
import java.time.LocalDate

object StarDiscount : Promotion {
    override fun discountAmount(orders: Orders): Money {
        if (isStarDay(orders.date)) {
            return Money.longValueOf(1000, Currency.KRW)
        }

        return Money.longValueOf(0, Currency.KRW)
    }

    override fun giveawayProduct(orders: Orders): Product? {
        return null
    }

    private fun isStarDay(date: LocalDate): Boolean {
        return date.dayOfWeek == DayOfWeek.SUNDAY
                || date == LocalDate.of(2023, 12, 25)
    }
}
