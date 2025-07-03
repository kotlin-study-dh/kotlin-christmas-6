package christmas.domain.discount

import christmas.domain.menu.Money
import christmas.domain.order.Order
import org.junit.jupiter.api.Test
import support.MenuDummy

class WeekendDiscounterTest {

    @Test
    fun `discount 2023 won each main food`() {
        // given
        val order = Order(
            listOf(
                MenuDummy.createMain("T-BORN", 55_000),
                MenuDummy.createMain("BARBECUE", 54_000)
            )
        )
        val discounter = WeekendDiscounter(order)

        // when
        val actual = discounter.discount()

        // then
        val expect = order.aggregatePurchaseAmount() - Money(WeekendDiscounter.FIXED_DISCOUNT_COST * 2)
        assert(actual == expect)
    }
}