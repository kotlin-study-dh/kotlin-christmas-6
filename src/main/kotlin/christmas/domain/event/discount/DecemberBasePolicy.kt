package christmas.domain.event.discount

import christmas.domain.order.Order


object DecemberDiscountBasePolicy {
    private val MIN_PRICE_FOR_EVENT = 10_000

    fun isEligibleForDiscount(order: Order) = order.totalPlacedPrice.value >= MIN_PRICE_FOR_EVENT
}