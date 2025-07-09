package christmas.domain

import java.time.DayOfWeek
import java.time.LocalDate

class Order(
    val menus: Map<Menu, Int>,
    private val orderDate: Date
) {

    constructor(menus: Map<Menu, Int>, orderDate: Int) : this(menus, Date(orderDate))

    fun getDayOfWeek(): DayOfWeek {
        require(date in DECEMBER_START_DATE..DECEMBER_END_DATE) {
            "date must be between $DECEMBER_START_DATE and $DECEMBER_END_DATE"
        }
        val decemberFirst = LocalDate.of(EVENT_YEAR, DECEMBER, DECEMBER_START_DATE)
        return decemberFirst.plusDays((date - 1).toLong()).dayOfWeek
    }

    fun countMenuByCategory(category: MenuCategory) =
        menus.filter { it.key.category == category }.values.sum()

    val totalPrice: Int
        get() = menus.entries.sumOf { (menu, count) -> menu.price * count }

    val date: Int
        get() = orderDate.date

    companion object {
        private const val DECEMBER_START_DATE = 1
        private const val DECEMBER_END_DATE = 31
        private const val EVENT_YEAR = 2023
        private const val DECEMBER = 12
    }
}
