package christmas.domain

import christmas.domain.discount.*
import christmas.domain.order.Reservation

class DiscountSelector(reservation: Reservation) {

    val discounters: List<Discounter> = listOf(
        PresentDiscounter(reservation),
        SpecialDiscounter(reservation),
        WeekendDiscounter(reservation),
        WeekdayDiscounter(reservation)
    ).filter { it.isApplicable() }
}