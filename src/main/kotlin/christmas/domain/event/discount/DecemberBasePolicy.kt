package christmas.domain.event.discount

import christmas.domain.order.Order
import java.time.LocalDate


object DecemberDiscountBasePolicy {
    private val START_DATE = LocalDate.of(2023, 12, 1)
    private val END_DATE = LocalDate.of(2023, 12, 31)
    private const val MIN_PRICE_FOR_EVENT = 10_000

    fun isEligibleForDiscount(order: Order) =
        order.placedDate in START_DATE..END_DATE
                && order.totalPlacedPrice.value >= MIN_PRICE_FOR_EVENT
}