package christmas.domain.promotion

import christmas.domain.money.Money
import christmas.domain.order.Orders
import christmas.domain.product.Product

interface Promotion {
    fun discountAmount(orders: Orders): Money
    fun giveawayProduct(orders: Orders): Product?
}
