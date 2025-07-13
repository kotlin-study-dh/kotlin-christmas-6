package christmas.domain.menu.repository

import christmas.domain.menu.Category
import christmas.domain.menu.Menu
import christmas.domain.menu.Money

object MenuRepository {

    val menus: List<Menu> = listOf(
        Menu("양송이수프", Money(6_000L), Category.APPETIZER),
        Menu("타파스", Money(5_500L), Category.APPETIZER),
        Menu("시저샐러드", Money(8_000L), Category.APPETIZER),

        Menu("티본스테이크", Money(55_000L), Category.MAIN),
        Menu("바비큐립", Money(54_000L), Category.MAIN),
        Menu("해산파스타", Money(35_000L), Category.MAIN),
        Menu("크리스마파스타", Money(25_000L), Category.MAIN),

        Menu("초코케이크", Money(15_000L), Category.DESSERT),
        Menu("아이스크림", Money(5_000L), Category.DESSERT),

        Menu("제로콜라", Money(3_000L), Category.BEVERAGE),
        Menu("레드와인", Money(60_000L), Category.BEVERAGE),
        Menu("샴페인", Money(25_000L), Category.BEVERAGE)
    )

    fun findByCategory(category: Category): List<Menu> {
        return menus.filter { it.category == category }
    }

    fun findByName(name: String): Menu {
        val find = menus.find { it.name == name }
        if (find == null) {
            throw IllegalArgumentException("등록되지 않은 메뉴입니다.")
        }
        return find
    }
}