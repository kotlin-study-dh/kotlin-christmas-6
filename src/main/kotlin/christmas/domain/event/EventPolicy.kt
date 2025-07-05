package christmas.domain.event

import christmas.domain.order.Order

interface EventPolicy {
    fun isEligibleFor(order: Order): Boolean
}