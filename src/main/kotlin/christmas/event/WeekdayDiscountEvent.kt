package christmas.event

import christmas.menu.Category
import christmas.menu.Order

object WeekdayDiscountEvent {
    private const val MIN_ORDER_AMOUNT = 10_000
    private const val DISCOUNT_AMOUNT_PER_MENU = 2_023
    private const val NO_DISCOUNT = 0
    private val discountCategory = Category.DESSERT

    fun calculateDiscountAmount(order: Order): Int {
        if (order.totalOrderAmount < MIN_ORDER_AMOUNT) {
            return NO_DISCOUNT
        }
        if (order.isWeekday) {
            return order.sumMenuCountsOf(discountCategory) * DISCOUNT_AMOUNT_PER_MENU
        }
        return NO_DISCOUNT
    }
}
