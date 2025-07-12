package christmas.event

import christmas.order.Order

abstract class AbstractChristmasEvent : ChristmasEvent {
    final override fun calculateBenefitAmount(order: Order): Int {
        if (order.totalOrderAmount < MIN_ORDER_AMOUNT) {
            return NO_DISCOUNT
        }
        if (isApplicable(order)) {
            return calculateInternal(order)
        }
        return NO_DISCOUNT
    }

    abstract fun isApplicable(order: Order): Boolean

    abstract fun calculateInternal(order: Order): Int

    companion object {
        private const val MIN_ORDER_AMOUNT = 10_000
        private const val NO_DISCOUNT = 0
    }
}
