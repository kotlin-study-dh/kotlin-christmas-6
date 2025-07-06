package christmas.event

import christmas.menu.Menu
import christmas.menu.Order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class SpecialDiscountEventTest {
    private val event = SpecialDiscountEvent
    private val specialDay = LocalDate.of(2023, 12, 3)

    @Test
    fun `calculates discount amount for a special day order`() {
        // given
        val menuAndCounts = mapOf(
            Menu.TAPAS to 1,
            Menu.T_BONE_STAKE to 1,
            Menu.CHOCOLATE_CAKE to 1
        )
        val order = Order(menuAndCounts, specialDay)

        // when
        val benefitAmount = event.calculateBenefitAmount(order)

        // then
        assertThat(benefitAmount).isEqualTo(1_000)
    }

    @Test
    fun `does not calculate discount amount when order amount is below minimum`() {
        // given
        val menuAndCounts = mapOf(
            Menu.TAPAS to 1
        )
        val order = Order(menuAndCounts, specialDay)

        // when
        val benefitAmount = event.calculateBenefitAmount(order)

        // then
        assertThat(benefitAmount).isEqualTo(0)
    }

    @Test
    fun `does not calculate discount amount when order is not on a special day`() {
        // given
        val nonSpecialDay = LocalDate.of(2023, 12, 4)
        val menuAndCounts = mapOf(
            Menu.TAPAS to 1,
            Menu.T_BONE_STAKE to 1
        )
        val order = Order(menuAndCounts, nonSpecialDay)

        // when
        val benefitAmount = event.calculateBenefitAmount(order)

        // then
        assertThat(benefitAmount).isEqualTo(0)
    }
}
