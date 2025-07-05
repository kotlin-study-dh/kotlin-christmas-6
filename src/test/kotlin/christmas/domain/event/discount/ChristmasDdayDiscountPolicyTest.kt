package christmas.domain.event.discount

import christmas.domain.Menu
import christmas.domain.Price
import christmas.domain.order.Order
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate

class ChristmasDdayDiscountPolicyTest {

    @ParameterizedTest
    @MethodSource("validDates")
    fun `return true when placed date belongs to event period`(date: LocalDate) {
        val order = Order(date, mapOf(Menu.MUSHROOM_SOUP to 1, Menu.T_BONE_STEAK to 1))

        val actual = ChristmasDdayDiscountPolicy.isEligibleFor(order)

        Assertions.assertThat(actual).isTrue
    }

    @Test
    fun `calculate discount amount for given order`() {
        val placedDate = LocalDate.of(2023, 12, 1)
        val order = Order(placedDate, mapOf(Menu.MUSHROOM_SOUP to 1, Menu.T_BONE_STEAK to 2))

        val discountAmount = ChristmasDdayDiscountPolicy.getDiscountAmount(order)

        Assertions.assertThat(discountAmount).isEqualTo(Price.Companion.from(1_000))
    }

    companion object {
        @JvmStatic
        fun validDates(): List<LocalDate> = listOf(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 25))
    }
}