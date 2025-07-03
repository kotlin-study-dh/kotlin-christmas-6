package christmas.domain.order

import christmas.domain.menu.Category
import org.junit.jupiter.api.Test
import support.MenuDummy

class OrderTest {

    @Test
    fun `calculate total purchase amount of menus`() {
        // given
        val price1 = 5_000L
        val price2 = 55_000L
        val price3 = 15_000L
        val price4 = 3_000L
        val order = Order(
            listOf(
                MenuDummy.createAppetizer("TAPAS", price1),
                MenuDummy.createMain("T-BORN", price2),
                MenuDummy.createDessert("CHOCOLATE-CAKE", price3),
                MenuDummy.createBeverage("DIET-COKE", price4)
            )
        )

        // when
        val actual = order.aggregatePurchaseAmount()

        // then
        val expect = price1 + price2 + price3 + price4
        assert(expect == actual.money)
    }

    @Test
    fun `get count of specific menu category`() {
        // given
        val order = Order(
            listOf(
                MenuDummy.createAppetizer("TAPAS", 5_000L),
                MenuDummy.createMain("T-BORN", 55_000L),
                MenuDummy.createMain("BARBECUE-LIP", 54_000L),
                MenuDummy.createDessert("CHOCOLATE-CAKE", 15_00L)
            )
        )

        // when
        val count = order.count(Category.MAIN)

        // then
        assert(count == 2)
    }

}