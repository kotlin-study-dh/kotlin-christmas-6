package christmas.domain.event

import christmas.domain.MenuSection
import christmas.domain.Price
import christmas.domain.order.OrderContext
import java.time.DayOfWeek

object WeekdayDiscountPolicy : DiscountPolicy {
    override val name = "Weekday Discount"
    override fun isEligibleFor(orderContext: OrderContext): Boolean {
        if (!DecemberDiscountBasePolicy.isEligibleForDiscount(orderContext)) {
            return false
        }
        return orderContext.placedDate.dayOfWeek in setOf(
            DayOfWeek.SUNDAY,
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
        )
    }

    override fun getBenefitAmount(orderContext: OrderContext): Price {
        require(isEligibleFor(orderContext)) {
            "This order is not eligible for this discount policy."
        }
        val dessertCount = orderContext.orderItems.keys
            .filter { menu -> menu.section == MenuSection.DESSERT }
            .mapNotNull { menu -> orderContext.orderItems[menu] }
            .sum()

        return Price.from(2_023) times dessertCount
    }
}