package christmas.event

import christmas.menu.Category
import christmas.menu.Order

object WeekendDiscountEvent : AbstractChristmasEvent() {
    private const val DISCOUNT_AMOUNT_PER_MENU = 2_023
    private val discountCategory = Category.MAIN_COURSE

    override fun isApplicable(order: Order) = order.isWeekend

    override fun calculateInternal(order: Order) = order.sumMenuCountsOf(discountCategory) * DISCOUNT_AMOUNT_PER_MENU

    override fun signature() = EventSignature.WEEKEND_DISCOUNT
}
