package christmas.event

import christmas.order.Order

interface ChristmasEvent {
    fun calculateBenefitAmount(order: Order): Int

    fun signature(): EventSignature
}
