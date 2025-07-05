package christmas.domain.event

import christmas.domain.Price
import christmas.domain.order.OrderContext
import java.time.LocalDate


object ChristmasDdayDiscountPolicy : DiscountPolicy {
    override val name = "Christmas D-Day Discount"
    private val START_DATE = LocalDate.of(2023, 12, 1)
    private val END_DATE = LocalDate.of(2023, 12, 25)
    private const val BASE_BENEFIT_AMOUNT = 1_000
    private const val INCREMENT_PER_DAY = 100

    override fun isEligibleFor(orderContext: OrderContext): Boolean {
        if (!DecemberDiscountBasePolicy.isEligibleForDiscount(orderContext)) {
            return false
        }
        return orderContext.placedDate in START_DATE..END_DATE
    }

    override fun getBenefitAmount(orderContext: OrderContext): Price {
        require(isEligibleFor(orderContext)) { "This order is not eligible for this discount policy." }
        val daysBetween = orderContext.placedDate.toEpochDay() - START_DATE.toEpochDay()
        return Price.from(BASE_BENEFIT_AMOUNT + (daysBetween.toInt() * INCREMENT_PER_DAY))
    }
}