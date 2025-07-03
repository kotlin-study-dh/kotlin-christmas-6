package christmas.domain.discount

import christmas.domain.menu.Money
import christmas.domain.order.Order
import org.junit.jupiter.api.Test
import support.MenuDummy

class WeekdayDiscounterTest {

    @Test
    fun `weekday discount 2023 won by each dessert`() {
        val order = Order(
            listOf(
                MenuDummy.createDessert("CHOCOLATE-CAKE", 15_000),
                MenuDummy.createMain("BARBECUE", 54_000)
            )
        )
        val discounter = WeekdayDiscounter(order)

        // when
        val actual = discounter.discount()

        // then
        val expect = order.aggregatePurchaseAmount() - Money(WeekendDiscounter.FIXED_DISCOUNT_COST)
        assert(actual == expect)
    }
}
