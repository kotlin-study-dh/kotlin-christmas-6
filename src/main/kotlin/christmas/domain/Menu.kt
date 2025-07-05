package christmas.domain

enum class Menu(
    val displayName: String,
    val price: Price,
    val section: MenuSection
) {
    MUSHROOM_SOUP("Mushroom Soup", Price.from(6_000), MenuSection.APPETIZER),
    TAPAS("Tapas", Price.from(5_500), MenuSection.APPETIZER),
    CAESAR_SALAD("Caesar Salad", Price.from(8_000), MenuSection.APPETIZER),
    T_BONE_STEAK("T-bone Steak", Price.from(55_000), MenuSection.MAIN),
    BBQ_RIBS("BBQ Ribs", Price.from(54_000), MenuSection.MAIN),
    SEAFOOD_PASTA("Seafood Pasta", Price.from(35_000), MenuSection.MAIN),
    CHRISTMAS_PASTA("Christmas Pasta", Price.from(25_000), MenuSection.MAIN),
    CHOCOLATE_CAKE("Chocolate Cake", Price.from(15_000), MenuSection.DESSERT),
    ICE_CREAM("Ice Cream", Price.from(5_000), MenuSection.DESSERT),
    COKE_ZERO("Coke Zero", Price.from(3_000), MenuSection.BEVERAGE),
    RED_WINE("Red Wine", Price.from(60_000), MenuSection.BEVERAGE),
    CHAMPAGNE("Champagne", Price.from(25_000), MenuSection.BEVERAGE), ;

    companion object {
        fun from(displayName: String): Menu? = Menu.values().first { it.displayName == displayName }
    }
}

enum class MenuSection {
    APPETIZER,
    MAIN,
    DESSERT,
    BEVERAGE,
    ;
}