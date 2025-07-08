package christmas.domain

enum class Menu(val category: MenuCategory, val itemName: String, val price: Int) {

    MUSHROOM_SOUP(MenuCategory.APPETIZER, "양송이수프", 6_000),
    TAPAS(MenuCategory.APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(MenuCategory.APPETIZER, "시저샐러드", 8_000),
    T_BONE_STEAK(MenuCategory.MAIN, "티본스테이크", 55_000),
    BARBECUE_RIBS(MenuCategory.MAIN, "바비큐립", 54_000),
    SEAFOOD_PASTA(MenuCategory.MAIN, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MenuCategory.MAIN, "크리스마스파스타", 25_000),
    CHOCOLATE_CAKE(MenuCategory.DESSERT, "초코케이크", 15_000),
    ICE_CREAM(MenuCategory.DESSERT, "아이스크림", 5_000),
    ZERO_COKE(MenuCategory.DRINKS, "제로콜라", 3_000),
    RED_WINE(MenuCategory.DRINKS, "레드와인", 60_000),
    CHAMPAGNE(MenuCategory.DRINKS, "샴페인", 25_000);

    companion object {
        fun findMenu(name: String) = Menu.entries.find { it.itemName == name }
            ?: throw IllegalArgumentException("Not exist menu: $name")
    }
}

enum class MenuCategory {
    APPETIZER, MAIN, DESSERT, DRINKS
}
