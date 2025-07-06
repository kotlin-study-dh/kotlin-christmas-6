package christmas.event

import christmas.menu.Order

object DDayDiscountEvent : AbstractChristmasEvent() {
    private const val BASE_DISCOUNT_AMOUNT = 1_000
    private const val DISCOUNT_INCREMENT_PER_DAY = 100
    private val availableDays = 1..25

    override fun isApplicable(order: Order): Boolean {
        return order.isDayMatched(availableDays::contains)
    }

    override fun calculateInternal(order: Order): Int {
        return BASE_DISCOUNT_AMOUNT + DISCOUNT_INCREMENT_PER_DAY * (order.day - 1)
    }
}
