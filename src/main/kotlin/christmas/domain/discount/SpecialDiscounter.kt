package christmas.domain.discount

import christmas.domain.menu.Money
import christmas.domain.order.Order

data class SpecialDiscounter(val order: Order) : Discounter {

    override fun discount(): Money {
        return order.aggregatePurchaseAmount() - Money(FIXED_DISCOUNT_COST)
    }

    companion object {
        const val FIXED_DISCOUNT_COST = 1_000L
    }
}