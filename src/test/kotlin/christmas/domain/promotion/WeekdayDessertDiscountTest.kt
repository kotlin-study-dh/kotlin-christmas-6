package christmas.domain.promotion

import christmas.configuration.promotion.WeekdayDessertDiscount
import christmas.domain.promotion.PromotionTest.Companion.dessertOrder
import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate
import java.util.stream.Stream

class WeekdayDessertDiscountTest : PromotionTest {
    val event = WeekdayDessertDiscount()

    @ParameterizedTest
    @MethodSource("weekdays")
    fun `weekday, 2 desserts - discount 4046 KRW`(date: LocalDate) {
        val orders = Orders(date, listOf(dessertOrder))
        val discountAmount = event.discountAmount(orders)

        Assertions.assertThat(discountAmount).isEqualTo(Money.longValueOf(4046, Currency.KRW))
    }

    @ParameterizedTest
    @MethodSource("weekends")
    fun `weekend, 2 desserts - discount 0 KRW`(date: LocalDate) {
        val orders = Orders(date, listOf(dessertOrder))
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
