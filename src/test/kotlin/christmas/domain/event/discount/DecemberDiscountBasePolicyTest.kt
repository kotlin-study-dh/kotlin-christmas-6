package christmas.domain.event.discount

import christmas.domain.Menu
import christmas.domain.order.Order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DecemberDiscountBasePolicyTest {

    @Test
    fun `evaluate invalid when the total price of the order is less than 10,000`() {
        // Given
        val order = Order(LocalDate.of(2023, 12, 5), mapOf(Menu.MUSHROOM_SOUP to 1))

        // When
        val actual = DecemberDiscountBasePolicy.isEligibleForDiscount(order)

        // Then
        assertThat(actual).isFalse()

    }

}