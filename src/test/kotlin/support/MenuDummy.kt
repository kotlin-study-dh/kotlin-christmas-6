package support

import christmas.domain.menu.Category
import christmas.domain.menu.Menu
import christmas.domain.menu.Money

object MenuDummy {

    fun createMain(name: String, price: Long): Menu {
        return create(name, price, Category.MAIN)
    }

    fun createAppetizer(name: String, price: Long): Menu {
        return create(name, price, Category.APPETIZER)
    }

    fun createDessert(name: String, price: Long): Menu {
        return create(name, price, Category.DESSERT)
    }

    fun createBeverage(name: String, price: Long): Menu {
        return create(name, price, Category.BEVERAGE)
    }

    private fun create(name: String, price: Long, category: Category): Menu {
        return Menu(name, Money(price), category)
    }
}