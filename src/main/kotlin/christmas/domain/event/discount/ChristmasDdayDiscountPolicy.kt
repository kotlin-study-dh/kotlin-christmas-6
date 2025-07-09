package christmas.domain.event.discount

import christmas.domain.order.Menu
import christmas.domain.order.Price
import java.time.LocalDate

object ChristmasDdayDiscountPolicy : DiscountPolicy() {
    override val name = "Christmas D-Day Discount"
    private val START_DATE = LocalDate.of(2023, 12, 1)
    private val END_DATE = LocalDate.of(2023, 12, 25)
    private const val BASE_BENEFIT_AMOUNT = 1_000
    private const val INCREMENT_PER_DAY = 100

    override fun checkSpecificEventConditions(placedDate: LocalDate, orderItems: Map<Menu, Int>): Boolean {
        return placedDate in START_DATE..END_DATE
    }

    override fun getBenefitAmount(placedDate: LocalDate, orderItems: Map<Menu, Int>): Price {
        require(isEligibleFor(placedDate, orderItems)) { "This order is not eligible for this discount policy." }
        val daysBetween = placedDate.toEpochDay() - START_DATE.toEpochDay()
        return Price.from(BASE_BENEFIT_AMOUNT + (daysBetween.toInt() * INCREMENT_PER_DAY))
    }
}