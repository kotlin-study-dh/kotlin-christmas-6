package christmas.domain.event.discount

import christmas.domain.Price
import christmas.domain.order.Order
import java.time.LocalDate

object ChristmasDdayDiscountPolicy : DiscountPolicy {
    private val START_DATE = LocalDate.of(2023, 12, 1)
    private val END_DATE = LocalDate.of(2023, 12, 25)

    override fun isEligibleFor(order: Order): Boolean {
        if (!DecemberDiscountBasePolicy.isEligibleForDiscount(order)) {
            return false
        }
        return order.placedDate in START_DATE..END_DATE
    }

    override fun getDiscountAmount(order: Order): Price {
        require(isEligibleFor(order)) { "This order is not eligible for this discount policy." }
        val daysBetween = START_DATE.toEpochDay() - order.placedDate.toEpochDay()
        return Price.Companion.from(1_000 + (daysBetween.toInt() * 100))
    }
}