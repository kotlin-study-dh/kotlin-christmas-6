package christmas.domain.event

import christmas.domain.order.OrderContext
import java.time.LocalDate


object DecemberDiscountBasePolicy {
    private val START_DATE = LocalDate.of(2023, 12, 1)
    private val END_DATE = LocalDate.of(2023, 12, 31)
    private const val MIN_PRICE_FOR_EVENT = 10_000

    fun isEligibleForDiscount(orderContext: OrderContext) =
        orderContext.placedDate in START_DATE..END_DATE
                && orderContext.totalPrice.value >= MIN_PRICE_FOR_EVENT
}