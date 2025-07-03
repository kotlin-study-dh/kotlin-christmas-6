package christmas.domain.order

import christmas.domain.Menu
import christmas.domain.Price
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class OrderTest {

    @Test
    fun `calculates the total price of the order`() {
        // Given
        val orderItems = mapOf(Menu.TAPAS to 1, Menu.COKE_ZERO to 1)
        val order = Order(LocalDate.now(), orderItems)

        // When
        val totalPrice: Price = order.calculatePrice()

        // Then
        assertThat(totalPrice).isEqualTo(Price.from(8_500))
    }

}