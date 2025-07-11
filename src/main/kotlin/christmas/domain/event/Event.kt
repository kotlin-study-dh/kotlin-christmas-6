package christmas.domain.event

import christmas.domain.money.Money
import christmas.domain.order.Orders
import christmas.domain.product.Product

interface Event {
    fun discountAmount(orders: Orders): Money
    fun giveawayProduct(orders: Orders): Product?
}
