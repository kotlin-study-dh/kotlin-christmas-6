package christmas.domain.promotion.strategy

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
import christmas.domain.product.Product

interface PromotionStrategy {
    fun discountAmount(orders: Orders): Money
    fun giveawayProduct(orders: Orders): Product?
    fun meetsMinimumOrderPrice(orders: Orders): Boolean {
        return orders.totalPrice() >= MINIMUM_ORDER_PRICE
    }

    companion object {
        private val MINIMUM_ORDER_PRICE = Money.longValueOf(10000, Currency.KRW)
    }
}
