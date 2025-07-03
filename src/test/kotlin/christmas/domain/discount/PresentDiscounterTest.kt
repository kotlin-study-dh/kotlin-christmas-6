package christmas.domain.discount

import christmas.domain.menu.Money
import christmas.domain.order.Order
import org.junit.jupiter.api.Test
import support.MenuDummy

class PresentDiscounterTest {

    @Test
    fun `if total purchase amount exceeds 120_000, present will offered`() {
        // given
        val order = Order(
            listOf(MenuDummy.createDessert("GOLD-CAKE", 120_000)),
        )
        val discounter = PresentDiscounter(order)

        // when
        val actual = discounter.discount()

        // then
        val except = Money(120_000 - PresentDiscounter.CHAMPAGNE_PRICE)
        assert(except == actual)
    }
}