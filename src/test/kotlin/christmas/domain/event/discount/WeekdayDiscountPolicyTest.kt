package christmas.domain.event.discount

import christmas.domain.Menu
import christmas.domain.Price
import christmas.domain.order.OrderContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class WeekdayDiscountPolicyTest {

    @Test
    fun `evaluate as eligible on weekday`() {
        // Given
        val weekday = LocalDate.of(2023, 12, 4)
        val order = OrderContext(placedDate = weekday, mapOf(Menu.TAPAS to 3))

        // When
        val actual = WeekdayDiscountPolicy.isEligibleFor(order)

        // Then
        assertThat(actual).isTrue()
    }

    @Test
    fun `evaluate as not eligible on weekend`() {
        // Given
        val weekend = LocalDate.of(2023, 12, 2)
        val order = OrderContext(placedDate = weekend, mapOf(Menu.TAPAS to 3))

        // When
        val actual = WeekdayDiscountPolicy.isEligibleFor(order)

        // Then
        assertThat(actual).isFalse()
    }

    @Test
    fun `calculates discounted price`() {
        val weekday = LocalDate.of(2023, 12, 4)
        val order = OrderContext(
            placedDate = weekday,
            orderItems = mapOf(
                Menu.MUSHROOM_SOUP to 2,
                Menu.BBQ_RIBS to 1,
                Menu.CHOCOLATE_CAKE to 2,
                Menu.ICE_CREAM to 1,
            )
        )

        // When
        val discountAmount = WeekdayDiscountPolicy.getBenefitAmount(order)

        // Then
        assertThat(discountAmount).isEqualTo(Price.from(2_023 * 3))
    }
}