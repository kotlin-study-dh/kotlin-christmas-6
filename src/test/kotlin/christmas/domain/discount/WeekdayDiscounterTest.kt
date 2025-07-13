package christmas.domain.discount

import christmas.domain.menu.Money
import christmas.domain.order.Order
import christmas.domain.order.Reservation
import org.junit.jupiter.api.Test
import support.DateDummy
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
        val reservation = Reservation(DateDummy.weekday, order)
        val discounter = WeekdayDiscounter(reservation)

        // when
        val actual = discounter.discount()

        // then
        val expect = Money(WeekendDiscounter.FIXED_DISCOUNT_COST)
        assert(actual == expect)
    }

    @Test
    fun `weekday is applicable`() {
        // given
        val order = Order(
            listOf(
                MenuDummy.createDessert("CHOCOLATE-CAKE", 15_000),
                MenuDummy.createMain("BARBECUE", 54_000)
            )
        )
        val reservation = Reservation(DateDummy.weekday, order)
        val discounter = WeekdayDiscounter(reservation)

        // when
        val applicable = discounter.isApplicable()

        // then
        assert(applicable)
    }

    @Test
    fun `weekend is not applicable`() {
        // given
        val order = Order(
            listOf(
                MenuDummy.createDessert("CHOCOLATE-CAKE", 15_000),
                MenuDummy.createMain("BARBECUE", 54_000)
            )
        )
        val reservation = Reservation(DateDummy.weekend, order)
        val discounter = WeekdayDiscounter(reservation)

        // when
        val applicable = discounter.isApplicable()

        // then
        assert(!applicable)
    }
}
