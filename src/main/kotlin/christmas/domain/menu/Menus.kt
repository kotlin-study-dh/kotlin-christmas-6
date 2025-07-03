package christmas.domain.menu

data class Menus(val menus: List<Menu>) {

    init {
        val distinct = menus.map { it.name }
            .distinct()
        require(distinct.size == menus.size) {
            "menu names must not be duplicated"
        }
    }

    fun findByCategory(category: Category): List<Menu> {
        return menus.filter { it.category == category }
    }
}