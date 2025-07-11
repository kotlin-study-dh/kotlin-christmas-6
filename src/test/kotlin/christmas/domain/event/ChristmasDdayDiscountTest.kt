package christmas.domain.event

import christmas.domain.event.EventTest.Companion.order1
import christmas.domain.event.EventTest.Companion.order2
import christmas.domain.event.EventTest.Companion.order3
import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDate

class ChristmasDdayDiscountTest : EventTest {

    @ParameterizedTest
    @CsvSource("1,1000", "25,3400", "26,0")
    fun discountAmount(day: Int, amount: Long) {
        // given
        val orders = Orders(LocalDate.of(2023, 12, day), listOf(order1, order2, order3))
        val event = ChristmasDdayDiscount()

        // when
        val discountAmount = event.discountAmount(orders)

        // then
        assert(discountAmount == Money.longValueOf(amount, Currency.KRW))
    }

    @Test
    fun giveawayProduct() {
        // given
        val orders = Orders(LocalDate.of(2023, 12, 1), listOf(order1, order2, order3))
        val event = ChristmasDdayDiscount()

        // when
        val giveaway = event.giveawayProduct(orders)

        // then
        assert(giveaway == null)
    }
}
