package christmas.domain.order

import christmas.domain.menu.Category
import christmas.domain.menu.Money
import java.time.DayOfWeek
import java.time.LocalDate

data class Reservation(val date: LocalDate, val order: Order) {

    init {
        val after = LocalDate.of(2023, 11, 30)
        val before = LocalDate.of(2024, 1, 1)
        require(date.isAfter(after) && date.isBefore(before)) {
            "valid date must be between $after and $before"
        }
    }

    val weekend: Boolean
        get() = date.dayOfWeek === DayOfWeek.SATURDAY || date.dayOfWeek === DayOfWeek.FRIDAY

    val weekday: Boolean
        get() = date.dayOfWeek !== DayOfWeek.SATURDAY && date.dayOfWeek !== DayOfWeek.FRIDAY

    val sunday: Boolean
        get() = date.dayOfWeek === DayOfWeek.SUNDAY

    fun isSameDate(other: LocalDate): Boolean {
        return date == other
    }

    fun aggregatePurchaseAmount(): Money {
        return order.aggregatePurchaseAmount()
    }

    fun countMenuByCategory(category: Category): Int {
        return order.count(category)
    }
}