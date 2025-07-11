package christmas.domain.event

import christmas.domain.event.util.DayChecker.isWeekend
import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
import christmas.domain.product.Product
import christmas.domain.product.ProductType

class WeekendMainDishDiscount : Event {
    companion object {
        private val DISCOUNT_AMOUNT = Money.longValueOf(2023, Currency.KRW)
    }

    override fun discountAmount(orders: Orders): Money {
        val dayOfWeek = orders.date.dayOfWeek

        if (dayOfWeek.isWeekend()) {
            val mainCount = countMainDishes(orders)
            val totalDiscountMoney = DISCOUNT_AMOUNT.multiply(mainCount)
            return totalDiscountMoney
        }

        return Money.longValueOf(0, Currency.KRW)
    }

    override fun giveawayProduct(orders: Orders): Product? {
        return null
    }

    private fun countMainDishes(orders: Orders): Int {
        return orders.orders
            .filter { it.product.productType == ProductType.MAIN }
            .sumOf { order -> order.amount }
    }
}
