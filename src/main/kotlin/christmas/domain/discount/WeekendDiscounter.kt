package christmas.domain.discount

import christmas.domain.menu.Category
import christmas.domain.menu.Money
import christmas.domain.order.Order

data class WeekendDiscounter(val order: Order) : Discounter {

    override fun discount(): Money {
        val count = order.count(Category.MAIN)
        val totalDiscount = Money(FIXED_DISCOUNT_COST * count)
        return order.aggregatePurchaseAmount() - totalDiscount
    }

    companion object {
        const val FIXED_DISCOUNT_COST = 2_023L
    }
}