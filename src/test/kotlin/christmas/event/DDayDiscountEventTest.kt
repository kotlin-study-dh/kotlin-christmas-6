package christmas.event

import christmas.menu.Menu
import christmas.order.Order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDate

class DDayDiscountEventTest {
    private val event = DDayDiscountEvent
    private val dDay = LocalDate.of(2023, 12, 25)

    @ParameterizedTest
    @CsvSource(
        "1, 1000",
        "10, 1900",
        "20, 2900",
        "25, 3400"
    )
    fun `calculates discount amount for a D-Day order`(day: Int, expectedDiscountAmount: Int) {
        // given
        val menuAndCounts = mapOf(
            Menu.TAPAS to 1,
            Menu.T_BONE_STAKE to 1,
            Menu.CHOCOLATE_CAKE to 1
        )
        val order = Order(menuAndCounts, LocalDate.of(2023, 12, day))

        // when
        val benefitAmount = event.calculateBenefitAmount(order)

        // then
        assertThat(benefitAmount).isEqualTo(expectedDiscountAmount)
    }

    @Test
    fun `does not calculate discount amount when order amount is below minimum`() {
        // given
        val menuAndCounts = mapOf(
            Menu.TAPAS to 1
        )
        val order = Order(menuAndCounts, dDay)

        // when
        val benefitAmount = event.calculateBenefitAmount(order)

        // then
        assertThat(benefitAmount).isEqualTo(0)
    }

    @Test
    fun `does not calculate discount amount when order is not on D-Day`() {
        // given
        val nonDDay = LocalDate.of(2023, 12, 26)
        val menuAndCounts = mapOf(
            Menu.TAPAS to 1,
            Menu.T_BONE_STAKE to 1
        )
        val order = Order(menuAndCounts, nonDDay)

        // when
        val benefitAmount = event.calculateBenefitAmount(order)

        // then
        assertThat(benefitAmount).isEqualTo(0)
    }
}
