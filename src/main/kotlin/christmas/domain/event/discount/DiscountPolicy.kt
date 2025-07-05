package christmas.domain.event.discount

import christmas.domain.Price
import christmas.domain.event.EventPolicy
import christmas.domain.order.OrderContext

interface DiscountPolicy: EventPolicy {
    fun getBenefitAmount(orderContext: OrderContext): Price
}