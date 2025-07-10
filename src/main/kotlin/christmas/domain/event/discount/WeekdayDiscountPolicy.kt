package christmas.domain.event.discount

import christmas.domain.order.Menu
import christmas.domain.order.MenuSection
import christmas.domain.order.Price
import java.time.DayOfWeek
import java.time.LocalDate

object WeekdayDiscountPolicy : DiscountPolicy() {
    override val name = "Weekday Discount"
    private const val DISCOUNT_AMOUNT_PER_MENU = 2_023

    override fun checkSpecificEventConditions(placedDate: LocalDate, orderItems: Map<Menu, Int>): Boolean {
        return placedDate.isWeekday()
    }

    override fun getBenefitAmount(placedDate: LocalDate, orderItems: Map<Menu, Int>): Price {
        require(isEligibleFor(placedDate, orderItems)) {
            "This order is not eligible for this discount policy."
        }
        val dessertCount = orderItems.keys
            .filter { menu -> menu.section == MenuSection.DESSERT }
            .mapNotNull { menu -> orderItems[menu] }
            .sum()

        return Price.from(DISCOUNT_AMOUNT_PER_MENU) times dessertCount
    }
}

fun LocalDate.isWeekday() = this.dayOfWeek in setOf(
    DayOfWeek.SUNDAY,
    DayOfWeek.MONDAY,
    DayOfWeek.TUESDAY,
    DayOfWeek.WEDNESDAY,
    DayOfWeek.THURSDAY,
)