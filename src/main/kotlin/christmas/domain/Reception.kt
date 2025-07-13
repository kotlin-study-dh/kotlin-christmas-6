package christmas.domain

import christmas.domain.discount.*
import christmas.domain.menu.Money
import christmas.domain.menu.repository.MenuRepository
import christmas.domain.order.Order
import christmas.domain.order.Reservation
import java.time.LocalDate

class Reception(reservationDay: Int, demands: List<String>) {

    val reservation = {
        require(demands.isNotEmpty()) { "유효하지 않은 주문입니다." }
        val menus = demands.map { MenuRepository.findByName(it) }
        val order = Order(menus)
        Reservation(LocalDate.of(2023, 12, reservationDay), order)
    }()

    fun aggregateNetPrice(): Long {
        return aggregatePurchaseAmount() - aggregateDiscountWithoutPresent()
    }

    fun aggregateTotalDiscount(): Long {
        return applyDdayDiscount() + applyWeekdayDiscount() + applyWeekendDiscount() + applyPresentDiscount() + applySpecialDiscount()
    }

    private fun aggregateDiscountWithoutPresent(): Long {
        return applyDdayDiscount() + applyWeekdayDiscount() + applyWeekendDiscount() + applySpecialDiscount()
    }

    fun aggregatePurchaseAmount(): Long {
        return reservation.aggregatePurchaseAmount().money
    }

    fun hasPresent(): Boolean {
        return applyDiscount(PresentDiscounter(reservation)) > 0
    }

    fun applyPresentDiscount(): Long {
        return applyDiscount(PresentDiscounter(reservation))
    }

    fun applyDdayDiscount(): Long {
        return applyDiscount(DdayDiscounter(reservation))
    }

    fun applyWeekdayDiscount(): Long {
        return applyDiscount(WeekdayDiscounter(reservation))
    }

    fun applyWeekendDiscount(): Long {
        return applyDiscount(WeekendDiscounter(reservation))
    }

    fun applySpecialDiscount(): Long {
        return applyDiscount(SpecialDiscounter(reservation))
    }

    private fun applyDiscount(discounter: Discounter): Long {
        if (discounter.isApplicable() && reservation.aggregatePurchaseAmount() > Money(10_000L)) {
            return discounter.discount().money
        }
        return 0L
    }
}