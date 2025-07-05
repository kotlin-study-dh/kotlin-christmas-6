package christmas.domain.event.discount

import christmas.domain.Price
import christmas.domain.order.Order
import java.time.LocalDate

object SpecialDiscountPolicy: DiscountPolicy {
    private val specialDays = setOf(
        LocalDate.of(2023, 12, 3),
        LocalDate.of(2023, 12, 10),
        LocalDate.of(2023, 12, 17),
        LocalDate.of(2023, 12, 24),
        LocalDate.of(2023, 12, 25),
        LocalDate.of(2023, 12, 31),
    )
    override fun isEligibleFor(order: Order): Boolean {
        return DecemberDiscountBasePolicy.isEligibleForDiscount(order)
                &&  order.placedDate in specialDays
    }

    override fun getDiscountAmount(order: Order): Price {
        return Price.from(1_000)
    }
}