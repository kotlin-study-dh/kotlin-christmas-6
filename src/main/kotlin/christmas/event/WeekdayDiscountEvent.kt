package christmas.event

import christmas.menu.Category
import christmas.menu.Order

object WeekdayDiscountEvent : AbstractChristmasEvent() {
    private const val DISCOUNT_AMOUNT_PER_MENU = 2_023
    private val discountCategory = Category.DESSERT

    override fun isApplicable(order: Order) = order.isWeekday

    override fun calculateInternal(order: Order) = order.sumMenuCountsOf(discountCategory) * DISCOUNT_AMOUNT_PER_MENU

    override fun signature() = EventSignature.WEEKDAY_DISCOUNT
}
