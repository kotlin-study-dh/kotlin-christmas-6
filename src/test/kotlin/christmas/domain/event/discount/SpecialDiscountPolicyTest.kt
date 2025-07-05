package christmas.domain.event.discount

import christmas.domain.Menu
import christmas.domain.order.Order
import christmas.domain.order.OrderContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class SpecialDiscountPolicyTest {

    @Test
    fun `evaluate as eligible on special day`() {
        // Given
        val specialDay = LocalDate.of(2023, 12, 10)
        val order = OrderContext(
            placedDate = specialDay,
            orderItems = mapOf(Menu.T_BONE_STEAK to 1)
        )

        // When
        val actual = SpecialDiscountPolicy.isEligibleFor(order)

        // Then
        assertThat(actual).isTrue()
    }

    @Test
    fun `evaluate as not eligible on special day`() {
        // Given
        val specialDay = LocalDate.of(2023, 12, 9)
        val order = OrderContext(
            placedDate = specialDay,
            orderItems = mapOf(Menu.T_BONE_STEAK to 1)
        )

        // When
        val actual = SpecialDiscountPolicy.isEligibleFor(order)

        // Then
        assertThat(actual).isFalse()
    }

}