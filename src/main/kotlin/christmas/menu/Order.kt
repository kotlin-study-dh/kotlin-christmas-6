package christmas.menu

import christmas.menu.Category.DRINK
import christmas.util.isWeekDay
import java.time.LocalDate

class Order(
    val menuAndCount: Map<Menu, Int>,
    val date: LocalDate,
) {
    val isWeekday: Boolean
        get() = date.isWeekDay()
    val totalOrderAmount: Int
        get() = menuAndCount.map { (it.key.price * it.value) }
            .sum()

    init {
        require(menuAndCount.size != menuAndCount.keys.filter { menu -> menu.isCategory(DRINK) }.size) {
            "Cannot order drinks only."
        }
        require(menuAndCount.values.all { count -> count >= MIN_MENU_COUNT }) {
            "Each menu count must be greater than or equal to $MIN_MENU_COUNT."
        }
        require(menuAndCount.values.sum() <= MAX_TOTAL_MENU_COUNT) {
            "Total menu count must not exceed $MAX_TOTAL_MENU_COUNT."
        }
    }

    fun sumMenuCountsOf(category: Category) =
        menuAndCount.filter { it.key.isCategory(category) }
            .values.sum()

    companion object {
        private const val MIN_MENU_COUNT = 1
        private const val MAX_TOTAL_MENU_COUNT = 20

        fun of(menuNameAndCount: List<Pair<String, Int>>, date: LocalDate): Order {
            require(menuNameAndCount.size == menuNameAndCount.map { it.first }.toSet().size) {
                "Menu must be unique."
            }
            val menuAndCount = menuNameAndCount.associate { (korName, count) ->
                Menu.from(korName) to count
            }
            return Order(menuAndCount, date)
        }
    }
}
