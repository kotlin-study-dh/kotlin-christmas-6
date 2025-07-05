package christmas.domain.event

import christmas.domain.MenuSection
import christmas.domain.Price
import christmas.domain.order.OrderContext
import java.time.DayOfWeek

object WeekendDiscountPolicy: DiscountPolicy {
    override val name = "Weekend Discount"
    override fun isEligibleFor(orderContext: OrderContext): Boolean {
        if (!DecemberDiscountBasePolicy.isEligibleForDiscount(orderContext)) {
            return false
        }
        return orderContext.placedDate.dayOfWeek in setOf(
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
        )
    }

    override fun getBenefitAmount(orderContext: OrderContext): Price {
        require(isEligibleFor(orderContext)) {
            "This order is not eligible for this discount policy."
        }
        val mainCount = orderContext.orderItems.keys
            .filter { menu -> menu.section == MenuSection.MAIN }
            .mapNotNull { menu -> orderContext.orderItems[menu] }
            .sum()

        return Price.from(2_023) times mainCount
    }
}