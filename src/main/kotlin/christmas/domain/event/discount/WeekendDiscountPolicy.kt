package christmas.domain.event.discount

import christmas.domain.order.Menu
import christmas.domain.order.MenuSection
import christmas.domain.order.Price
import java.time.DayOfWeek
import java.time.LocalDate

object WeekendDiscountPolicy: DiscountPolicy() {
    override val name = "Weekend Discount"
    private const val DISCOUNT_AMOUNT_PER_MENU = 2_023

    override fun checkSpecificEventConditions(placedDate: LocalDate, orderItems: Map<Menu, Int>): Boolean {
        return placedDate.dayOfWeek in setOf(
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
        )
    }

    override fun getBenefitAmount(placedDate: LocalDate, orderItems: Map<Menu, Int>): Price {
        require(isEligibleFor(placedDate, orderItems)) {
            "This order is not eligible for this discount policy."
        }
        val mainCount = orderItems.keys
            .filter { menu -> menu.section == MenuSection.MAIN }
            .mapNotNull { menu -> orderItems[menu] }
            .sum()

        return Price.from(DISCOUNT_AMOUNT_PER_MENU) times mainCount
    }
}

fun LocalDate.isWeekend() = this.dayOfWeek in setOf(
    DayOfWeek.FRIDAY,
    DayOfWeek.SATURDAY,
)