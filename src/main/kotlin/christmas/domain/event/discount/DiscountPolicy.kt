package christmas.domain.event.discount

import christmas.domain.Price
import christmas.domain.event.EventPolicy
import christmas.domain.order.Order

interface DiscountPolicy: EventPolicy {
    fun getDiscountAmount(order: Order): Price
}