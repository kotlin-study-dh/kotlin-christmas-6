package christmas.event

import christmas.menu.Order

object SpecialDiscountEvent : AbstractChristmasEvent() {
    private const val DISCOUNT_AMOUNT = 1_000
    private val specialDays = setOf(3, 10, 17, 24, 25, 31)

    override fun isApplicable(order: Order) = order.isDayMatched(specialDays::contains)

    override fun calculateInternal(order: Order) = DISCOUNT_AMOUNT
}
