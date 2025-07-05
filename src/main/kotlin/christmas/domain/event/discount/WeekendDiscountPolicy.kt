package christmas.domain.event.discount

import christmas.domain.MenuSection
import christmas.domain.Price
import christmas.domain.order.Order
import java.time.DayOfWeek

object WeekendDiscountPolicy: DiscountPolicy {
    override fun isEligibleFor(order: Order): Boolean {
        if (!DecemberDiscountBasePolicy.isEligibleForDiscount(order)) {
            return false
        }
        return order.placedDate.dayOfWeek in setOf(
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
        )
    }

    override fun getDiscountAmount(order: Order): Price {
        require(isEligibleFor(order)) {
            "This order is not eligible for this discount policy."
        }
        val mainCount = order.orderItems.keys
            .filter { menu -> menu.section == MenuSection.MAIN }
            .mapNotNull { menu -> order.orderItems[menu] }
            .sum()

        return Price.from(2_023) times mainCount
    }
}