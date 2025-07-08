package christmas.policy

import christmas.domain.MenuCategory
import christmas.domain.Order
import java.time.DayOfWeek

object WeeklyDiscount : DiscountPolicy {

    private const val WEEKLY_DISCOUNT_UNIT = 2023

    override fun calculateDiscount(order: Order): Int {
        val category = when (order.getDayOfWeek()) {
            DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY
                -> MenuCategory.DESSERT

            DayOfWeek.FRIDAY, DayOfWeek.SATURDAY -> MenuCategory.MAIN
        }

        return order.countMenuByCategory(category) * WEEKLY_DISCOUNT_UNIT
    }
}
