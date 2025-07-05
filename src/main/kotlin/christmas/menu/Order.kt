package christmas.menu

class Order(
    menuAndCount: List<Pair<String, Int>>,
) {
    val menuAndCount: Map<Menu, Int>

    init {
        require(menuAndCount.size == menuAndCount.map { it.first }.toSet().size) {
            "Menu must be unique."
        }
        this.menuAndCount = menuAndCount.associate { (korName, count) ->
            Menu.from(korName) to count
        }.also {
            require(it.size != it.keys.filter { menu -> menu.isDrink() }.size) {
                "Cannot order drinks only."
            }
            require(it.values.all { count -> count >= MIN_MENU_COUNT }) {
                "Each menu count must be greater than or equal to $MIN_MENU_COUNT."
            }
            require(it.values.sum() <= MAX_TOTAL_MENU_COUNT) {
                "Total menu count must not exceed $MAX_TOTAL_MENU_COUNT."
            }
        }
    }

    companion object {
        private const val MIN_MENU_COUNT = 1
        private const val MAX_TOTAL_MENU_COUNT = 20
    }
}
