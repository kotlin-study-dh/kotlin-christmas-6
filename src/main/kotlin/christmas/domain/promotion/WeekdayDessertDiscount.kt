package christmas.domain.promotion

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
import christmas.domain.product.Product
import christmas.domain.product.ProductType
import christmas.domain.promotion.util.DayChecker.isWeekday

object WeekdayDessertDiscount : Promotion {
    private val DISCOUNT_AMOUNT = Money.longValueOf(2023, Currency.KRW)

    override fun discountAmount(orders: Orders): Money {
        val dayOfWeek = orders.date.dayOfWeek

        if (dayOfWeek.isWeekday()) {
            val dessertCount = countDesserts(orders)
            val totalDiscountMoney = DISCOUNT_AMOUNT.multiply(dessertCount)
            return totalDiscountMoney
        }

        return Money.longValueOf(0, Currency.KRW)
    }

    override fun giveawayProduct(orders: Orders): Product? {
        return null
    }

    private fun countDesserts(orders: Orders): Int {
        return orders.orders
            .filter { it.product.productType == ProductType.DESSERT }
            .sumOf { order -> order.amount }
    }
}
