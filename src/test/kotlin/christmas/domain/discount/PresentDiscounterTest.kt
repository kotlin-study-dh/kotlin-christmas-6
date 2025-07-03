package christmas.domain.discount

import christmas.domain.menu.Money
import christmas.domain.order.Order
import christmas.domain.order.Reservation
import org.junit.jupiter.api.Test
import support.DateDummy
import support.MenuDummy

class PresentDiscounterTest {

    @Test
    fun `if total purchase amount exceeds 120_000, present will offered`() {
        // given
        val order = Order(
            listOf(MenuDummy.createDessert("GOLD-CAKE", 120_000)),
        )
        val discounter = PresentDiscounter(Reservation(DateDummy.weekday, order))

        // when
        val actual = discounter.discount()

        // then
        val except = Money(120_000 - PresentDiscounter.CHAMPAGNE_PRICE)
        assert(except == actual)
    }

    @Test
    fun `if reservation total purchase amount exceeds 120_000, it will be applicable`() {
        // given
        val order = Order(
            listOf(MenuDummy.createDessert("GOLD-CAKE", 120_000)),
        )
        val discounter = PresentDiscounter(Reservation(DateDummy.weekday, order))

        // when
        val applicable = discounter.isApplicable()

        // then
        assert(applicable)
    }

    @Test
    fun `if reservation total purchase amount less then 120_000, it won't be applicable`() {
        // given
        val order = Order(
            listOf(MenuDummy.createDessert("GOLD-CAKE", 119_999)),
        )
        val discounter = PresentDiscounter(Reservation(DateDummy.weekday, order))

        // when
        val applicable = discounter.isApplicable()

        // then
        assert(!applicable)
    }
}