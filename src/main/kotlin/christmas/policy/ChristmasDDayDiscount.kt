package christmas.policy

import christmas.domain.Order

object ChristmasDDayDiscount : DiscountPolicy {

    private const val CHRISTMAS_DISCOUNT_START_DATE = 1
    private const val CHRISTMAS_DISCOUNT_END_DATE = 25
    private const val CHRISTMAS_INITIAL_DISCOUNT = 1000
    private const val CHRISTMAS_DISCOUNT_UNIT = 100

    override fun calculateDiscount(order: Order): Int {
        return if (order.date in CHRISTMAS_DISCOUNT_START_DATE..CHRISTMAS_DISCOUNT_END_DATE) {
            CHRISTMAS_INITIAL_DISCOUNT + (order.date - 1) * CHRISTMAS_DISCOUNT_UNIT
        } else 0
    }
}
