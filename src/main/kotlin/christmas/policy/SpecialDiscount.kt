package christmas.policy

import christmas.domain.Order

object SpecialDiscount : DiscountPolicy {

    private val specialDiscountDate = listOf(3, 10, 17, 24, 25, 31)

    override fun calculateDiscount(order: Order) = if (order.date in specialDiscountDate) 1000 else 0
}
