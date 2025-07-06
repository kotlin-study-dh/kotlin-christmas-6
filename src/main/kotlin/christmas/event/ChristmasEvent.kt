package christmas.event

import christmas.menu.Order

interface ChristmasEvent {
    fun calculateBenefitAmount(order: Order): Int

    fun signature(): EventSignature
}
