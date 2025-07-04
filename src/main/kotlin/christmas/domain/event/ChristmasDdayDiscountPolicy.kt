package christmas.domain.event

import christmas.domain.Price
import christmas.domain.event.discount.DiscountPolicy
import christmas.domain.order.Order
import java.time.LocalDate

class ChristmasDdayDiscountPolicy: DiscountPolicy {
    override fun isEligibleForDiscount(order: Order): Boolean {
        val startDate = LocalDate.of(2023, 12, 1)
        val endDate = LocalDate.of(2023, 12, 25)
        return order.placedDate in startDate..endDate
    }

    override fun apply(order: Order): Price {
        TODO("Not yet implemented")
    }
}