package christmas.menu

import christmas.menu.Category.DRINK
import christmas.util.isWeekDay
import christmas.util.isWeekend
import java.time.LocalDate

class Order(
    private val _menuAndCounts: Map<Menu, Int>,
    private val date: LocalDate,
) {
    val menuAndCounts: Map<Menu, Int>
        get() = _menuAndCounts.toMap()
    val totalOrderAmount: Int
        get() = _menuAndCounts.map { (it.key.price * it.value) }
            .sum()
    val day: Int
        get() = date.dayOfMonth
    val isWeekday: Boolean
        get() = date.isWeekDay()
    val isWeekend: Boolean
        get() = date.isWeekend()

    init {
        require(_menuAndCounts.size != _menuAndCounts.keys.filter { menu -> menu.isCategory(DRINK) }.size) {
            "Cannot order drinks only."
        }
        require(_menuAndCounts.values.all { count -> count >= MIN_MENU_COUNT }) {
            "Each menu count must be greater than or equal to $MIN_MENU_COUNT."
        }
        require(_menuAndCounts.values.sum() <= MAX_TOTAL_MENU_COUNT) {
            "Total menu count must not exceed $MAX_TOTAL_MENU_COUNT."
        }
    }

    fun sumMenuCountsOf(category: Category) =
        _menuAndCounts.filter { it.key.isCategory(category) }
            .values.sum()

    fun isDayMatched(predicate: (Int) -> Boolean) = predicate(date.dayOfMonth)

    companion object {
        private const val MIN_MENU_COUNT = 1
        private const val MAX_TOTAL_MENU_COUNT = 20

        fun of(menuNameAndCounts: List<Pair<String, Int>>, date: LocalDate): Order {
            require(menuNameAndCounts.isNotEmpty()) {
                "Menu must not be empty."
            }
            require(menuNameAndCounts.size == menuNameAndCounts.map { it.first }.toSet().size) {
                "Menu must be unique."
            }
            val menuAndCounts = menuNameAndCounts.associate { (korName, count) ->
                Menu.from(korName) to count
            }
            return Order(menuAndCounts, date)
        }
    }
}
