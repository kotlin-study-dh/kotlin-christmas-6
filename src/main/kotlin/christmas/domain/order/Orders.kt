package christmas.domain.order

import christmas.domain.money.Money

data class Orders(
    val orders: List<Order>,
) {
    fun totalPrice(): Money {
        val totalAmount = orders.sumOf { it.orderPrice().amount }
        val currency = orders.first().currency()
        return Money(totalAmount, currency)
    }
}
