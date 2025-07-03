package christmas.domain.discount

import christmas.domain.menu.Money
import christmas.domain.order.Reservation
import java.time.LocalDate

data class SpecialDiscounter(val reservation: Reservation) : Discounter {

    override fun discount(): Money {
        return reservation.aggregatePurchaseAmount() - Money(FIXED_DISCOUNT_COST)
    }

    override fun isApplicable(): Boolean {
        return reservation.sunday || reservation.isSameDate(LocalDate.of(2023, 12, 25))
    }

    companion object {
        const val FIXED_DISCOUNT_COST = 1_000L
    }
}