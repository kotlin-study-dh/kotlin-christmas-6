package christmas.domain.discount

import christmas.domain.menu.Money
import christmas.domain.order.Order
import christmas.domain.order.Reservation
import org.junit.jupiter.api.Test
import support.DateDummy
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

        val reservation = Reservation(DateDummy.weekend, order)
        val discounter = WeekendDiscounter(reservation)

        // when
        val actual = discounter.discount()

        // then
        val expect = Money(WeekendDiscounter.FIXED_DISCOUNT_COST * 2)
        assert(actual == expect)
    }

    @Test
    fun `weekend is applicable`() {
        // given
        val order = Order(
            listOf(
                MenuDummy.createDessert("CHOCOLATE-CAKE", 15_000),
                MenuDummy.createMain("BARBECUE", 54_000)
            )
        )
        val reservation = Reservation(DateDummy.weekend, order)
        val discounter = WeekendDiscounter(reservation)

        // when
        val applicable = discounter.isApplicable()

        // then
        assert(applicable)
    }

    @Test
    fun `weekday is not applicable`() {
        // given
        val order = Order(
            listOf(
                MenuDummy.createDessert("CHOCOLATE-CAKE", 15_000),
                MenuDummy.createMain("BARBECUE", 54_000)
            )
        )
        val reservation = Reservation(DateDummy.weekday, order)
        val discounter = WeekendDiscounter(reservation)

        // when
        val applicable = discounter.isApplicable()

        // then
        assert(!applicable)
    }
}