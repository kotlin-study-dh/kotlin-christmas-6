package christmas.domain.discount

import christmas.domain.menu.Money
import christmas.domain.order.Reservation

data class PresentDiscounter(val reservation: Reservation) : Discounter {

    override fun discount(): Money {
        return Money(CHAMPAGNE_PRICE)
    }

    override fun isApplicable(): Boolean {
        return reservation.aggregatePurchaseAmount() >= Money(APPLICABLE_THRESHOLD)
    }

    companion object {
        const val CHAMPAGNE_PRICE = 25_000L
        const val APPLICABLE_THRESHOLD = 120_000L
    }
}