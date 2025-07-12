package christmas.domain.discount

import christmas.domain.menu.Category
import christmas.domain.menu.Money
import christmas.domain.order.Reservation

data class WeekendDiscounter(val reservation: Reservation) : Discounter {

    override fun discount(): Money {
        val count = reservation.countMenuByCategory(Category.MAIN)
        return Money(FIXED_DISCOUNT_COST * count)
    }

    override fun isApplicable(): Boolean {
        return reservation.weekend
    }

    companion object {
        const val FIXED_DISCOUNT_COST = 2_023L
    }
}