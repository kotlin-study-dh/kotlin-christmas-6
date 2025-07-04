package christmas.domain.event

import christmas.domain.Menu
import christmas.domain.order.Order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate

class ChristmasDdayDiscountPolicyTest {

    @ParameterizedTest
    @MethodSource("validDates")
    fun `return true when placed date belongs to event period`(date: LocalDate) {
        val christmasDdayDiscountPolicy = ChristmasDdayDiscountPolicy()
        val order = Order(date, mapOf(Menu.MUSHROOM_SOUP to 1))

        val actual = christmasDdayDiscountPolicy.isEligibleForDiscount(order)

        assertThat(actual).isTrue
    }

    companion object {
        @JvmStatic
        fun validDates(): List<LocalDate> = listOf(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 25))
    }
}