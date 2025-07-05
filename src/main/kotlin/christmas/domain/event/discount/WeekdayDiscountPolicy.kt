package christmas.domain.event.discount

import christmas.domain.MenuSection
import christmas.domain.Price
import christmas.domain.order.Order
import java.time.DayOfWeek

object WeekdayDiscountPolicy : DiscountPolicy {
    override fun isEligibleFor(order: Order): Boolean {
        if (!DecemberDiscountBasePolicy.isEligibleForDiscount(order)) {
            return false
        }
        return order.placedDate.dayOfWeek in setOf(
            DayOfWeek.SUNDAY,
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
        )
    }

    override fun getDiscountAmount(order: Order): Price {
        require(isEligibleFor(order)) {
            "This order is not eligible for this discount policy."
        }
        val dessertCount = order.orderItems.keys
            .filter { menu -> menu.section == MenuSection.DESSERT }
            .mapNotNull { menu -> order.orderItems[menu] }
            .sum()

        return Price.from(2_023) times dessertCount
    }
}