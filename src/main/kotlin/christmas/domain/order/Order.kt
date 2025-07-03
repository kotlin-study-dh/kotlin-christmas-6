package christmas.domain.order

import christmas.domain.menu.Category
import christmas.domain.menu.Menu
import christmas.domain.menu.Money

data class Order(val orders: List<Menu>) {

    fun aggregatePurchaseAmount(): Money {
        return orders
            .map { it.price }
            .reduce { acc, amount -> acc + amount }
    }

    fun count(category: Category): Int {
        return orders.filter { it.category == category }
            .size
    }
}