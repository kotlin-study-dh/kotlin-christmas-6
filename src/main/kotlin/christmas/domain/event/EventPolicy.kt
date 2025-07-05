package christmas.domain.event

import christmas.domain.Price
import christmas.domain.order.OrderContext

interface EventPolicy {
    fun isEligibleFor(orderContext: OrderContext): Boolean
    fun getBenefitAmount(orderContext: OrderContext): Price
}