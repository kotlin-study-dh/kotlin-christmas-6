package christmas.domain.event

import christmas.domain.event.EventTest.Companion.mainOrder
import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate
import java.util.stream.Stream

class WeekendMainDishDiscountTest {
    val event = WeekendMainDishDiscount()

    @ParameterizedTest
    @MethodSource("weekends")
    fun `weekend, 2 main dishes - discount 4046 KRW`(date: LocalDate) {
        val orders = Orders(date, listOf(mainOrder))
        val discountAmount = event.discountAmount(orders)
        assert(discountAmount == Money.longValueOf(4046, Currency.KRW))
    }

    @ParameterizedTest
    @MethodSource("weekdays")
    fun `weekday, 0 main dishes - discount 0 KRW`(date: LocalDate) {
        val orders = Orders(date, listOf(mainOrder))
        val discountAmount = event.discountAmount(orders)
        assert(discountAmount == Money.longValueOf(0, Currency.KRW))
    }

    companion object {
        @JvmStatic
        fun weekdays(): Stream<LocalDate> {
            return Stream.of(
                LocalDate.of(2023, 12, 3), // SUN
                LocalDate.of(2023, 12, 7), // THU
            )
        }

        @JvmStatic
        fun weekends(): Stream<LocalDate> {
            return Stream.of(
                LocalDate.of(2023, 12, 8), // FRI
                LocalDate.of(2023, 12, 9), // SAT
            )
        }
    }
}
