package christmas.domain.event.discount

import christmas.domain.Price
import christmas.domain.order.Order

interface DiscountPolicy {
    fun isEligibleForDiscount(order: Order): Boolean
    fun apply(order: Order): Price
}