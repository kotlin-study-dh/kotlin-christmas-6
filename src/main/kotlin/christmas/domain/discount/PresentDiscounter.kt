package christmas.domain.discount

import christmas.domain.menu.Money
import christmas.domain.order.Order

data class PresentDiscounter(val order: Order) : Discounter {

    override fun discount(): Money {
        return order.aggregatePurchaseAmount() - Money(CHAMPAGNE_PRICE)
    }

    companion object {
        const val CHAMPAGNE_PRICE = 25_000L
    }
}