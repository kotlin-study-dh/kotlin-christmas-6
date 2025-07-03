package christmas.domain.discount

import christmas.domain.menu.Money
import christmas.domain.order.Order
import org.junit.jupiter.api.Test
import support.MenuDummy

class SpecialDiscounterTest {

    @Test
    fun `it will discount 1_000 won`() {
        // given
        val order = Order(
            listOf(
                MenuDummy.createDessert("CHOCOLATE-CAKE", 15_000),
                MenuDummy.createMain("BARBECUE", 54_000)
            )
        )
        val discounter = SpecialDiscounter(order)

        // when
        val actual = discounter.discount()

        // then
        val expect = order.aggregatePurchaseAmount() - Money(SpecialDiscounter.FIXED_DISCOUNT_COST)
        assert(actual == expect)
    }

}