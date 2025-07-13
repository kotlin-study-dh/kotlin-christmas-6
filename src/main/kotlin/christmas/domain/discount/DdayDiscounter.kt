package christmas.domain.discount

import christmas.domain.menu.Money
import christmas.domain.order.Reservation
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class DdayDiscounter(val reservation: Reservation) : Discounter {

    override fun discount(): Money {
        val dayBetween = ChronoUnit.DAYS.between(LocalDate.of(2023, 12, 1), reservation.date)
        return Money(INIT_VALUE + dayBetween * INCREASE_STEP)
    }

    override fun isApplicable(): Boolean {
        return reservation.date.isBefore(LocalDate.of(2023, 12, 26))
    }

    companion object {
        const val INCREASE_STEP = 1_00L
        const val INIT_VALUE = 1_000L
    }
}