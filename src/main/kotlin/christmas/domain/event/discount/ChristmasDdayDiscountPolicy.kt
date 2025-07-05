package christmas.domain.event.discount

import christmas.domain.Price
import christmas.domain.order.Order
import christmas.domain.order.OrderContext
import java.time.LocalDate

object ChristmasDdayDiscountPolicy : DiscountPolicy {
    private val START_DATE = LocalDate.of(2023, 12, 1)
    private val END_DATE = LocalDate.of(2023, 12, 25)

    override fun isEligibleFor(orderContext: OrderContext): Boolean {
        if (!DecemberDiscountBasePolicy.isEligibleForDiscount(orderContext)) {
            return false
        }
        return orderContext.placedDate in START_DATE..END_DATE
    }

    override fun getBenefitAmount(orderContext: OrderContext): Price {
        require(isEligibleFor(orderContext)) { "This order is not eligible for this discount policy." }
        val daysBetween = START_DATE.toEpochDay() - orderContext.placedDate.toEpochDay()
        return Price.Companion.from(1_000 + (daysBetween.toInt() * 100))
    }
}