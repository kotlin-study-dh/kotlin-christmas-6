package christmas.menu

import christmas.menu.Category.APPETIZER
import christmas.menu.Category.DESSERT
import christmas.menu.Category.DRINK
import christmas.menu.Category.MAIN_COURSE

enum class Menu(
    private val category: Category,
    val korName: String,
    val price: Int,
) {
    BUTTON_MUSHROOM_SOUP(APPETIZER, "양송이수프", 6_000),
    TAPAS(APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(APPETIZER, "시저 샐러드", 8_000),
    T_BONE_STAKE(MAIN_COURSE, "티본 스테이크", 55_000),
    BARBECUE_RIBS(MAIN_COURSE, "바베큐 립", 54_000),
    SEAFOOD_PASTA(MAIN_COURSE, "해산물 파스타", 35_000),
    CHRISTMAS_PASTA(MAIN_COURSE, "크리스마스 파스타", 25_000),
    CHOCOLATE_CAKE(DESSERT, "초콜릿 케이크", 15_000),
    ICE_CREAM(DESSERT, "아이스크림", 5_000),
    ZERO_COKE(DRINK, "제로 콜라", 3_000),
    RED_WINE(DRINK, "레드 와인", 60_000),
    CHAMPAGNE(DRINK, "샴페인", 25_000),
    ;

    fun isCategory(category: Category) = this.category == category

    companion object {
        fun from(korName: String) =
            entries.find { it.korName == korName }
                ?: throw IllegalArgumentException("Cannot find menu for korName: $korName")
    }
}
