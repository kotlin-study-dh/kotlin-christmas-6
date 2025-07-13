package christmas.domain.discount

import christmas.domain.menu.Money
import christmas.domain.order.Order
import christmas.domain.order.Reservation
import org.junit.jupiter.api.Test
import support.DateDummy
import support.MenuDummy
import java.time.LocalDate

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
        val reservation = Reservation(DateDummy.specialDay, order)
        val discounter = SpecialDiscounter(reservation)

        // when
        val actual = discounter.discount()

        // then
        val expect = Money(SpecialDiscounter.FIXED_DISCOUNT_COST)
        assert(actual == expect)
    }

    @Test
    fun `every sunday, special discount is available`() {
        // given
        val order = Order(
            listOf(
                MenuDummy.createDessert("CHOCOLATE-CAKE", 15_000),
                MenuDummy.createMain("BARBECUE", 54_000)
            )
        )
        val reservation = Reservation(DateDummy.specialDay, order)
        val discounter = SpecialDiscounter(reservation)

        // when
        val applicable = discounter.isApplicable()

        // then
        assert(applicable)
    }

    @Test
    fun `Christmas is available`() {
        // given
        val order = Order(
            listOf(
                MenuDummy.createDessert("CHOCOLATE-CAKE", 15_000),
                MenuDummy.createMain("BARBECUE", 54_000)
            )
        )
        val reservation = Reservation(LocalDate.of(2023, 12, 25), order)
        val discounter = SpecialDiscounter(reservation)

        // when
        val applicable = discounter.isApplicable()

        // then
        assert(applicable)
    }

}