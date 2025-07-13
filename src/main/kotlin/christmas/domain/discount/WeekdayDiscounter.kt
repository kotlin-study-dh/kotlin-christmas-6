package christmas.domain.discount

import christmas.domain.menu.Category
import christmas.domain.menu.Money
import christmas.domain.order.Reservation

class WeekdayDiscounter(val reservation: Reservation) : Discounter {

    override fun discount(): Money {
        val count = reservation.countMenuByCategory(Category.DESSERT)
        return Money(FIXED_DISCOUNT_COST * count)
    }

    override fun isApplicable(): Boolean {
        return reservation.weekday
    }

    companion object {
        const val FIXED_DISCOUNT_COST = 2_023L
    }
}