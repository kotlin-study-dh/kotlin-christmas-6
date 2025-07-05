package christmas.domain.order

import christmas.domain.Menu
import christmas.domain.Price
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class OrderTest {

    @Test
    fun `calculates the total price of the order`() {
        // Given
        val orderItems = mapOf(Menu.TAPAS to 1, Menu.COKE_ZERO to 1)
        val order = Order.of(LocalDate.now(), orderItems)

        // When
        val totalPrice: Price = order.totalPlacedPrice

        // Then
        assertThat(totalPrice).isEqualTo(Price.from(8_500))
    }

    @Test
    fun `order item must not be empty`() {
        // Given
        val orderItems = emptyMap<Menu, Int>()

        // When & Then
        assertThrows<IllegalArgumentException> { Order.of(LocalDate.of(2023, 12, 1), orderItems) }
    }

    @Test
    fun `order item has to more than zero quantity`() {
        // Given
        val orderItems = mapOf(Menu.TAPAS to 0, Menu.COKE_ZERO to 1)

        // When & Then
        assertThrows<IllegalArgumentException> { Order.of(LocalDate.of(2023, 12, 1), orderItems) }
    }

    @Test
    fun `cannot order beverages only`() {
        // Given
        val orderItems = mapOf(Menu.COKE_ZERO to 1, Menu.RED_WINE to 2)

        // When & Then
        assertThrows<IllegalArgumentException> { Order.of(LocalDate.of(2023, 12, 1), orderItems) }
    }

    @Test
    fun `cannot order more than 20 menu items`() {
        // Given
        val orderItems = mapOf(Menu.TAPAS to 21)

        // When & Then
        assertThrows<IllegalArgumentException> { Order.of(LocalDate.of(2023, 12, 1), orderItems) }
    }

    @Test
    fun `calculates the total discounted price after applying discount policies`() {
        // Given
        val orderItems = mapOf(Menu.T_BONE_STEAK to 1, Menu.CHOCOLATE_CAKE to 1)
        val appliedPolicies = listOf(
            object : DiscountPolicy {
                override fun isEligibleFor(orderContext: OrderContext): Boolean = true
                override fun getBenefitAmount(context: OrderContext): Price = Price.from(1_000)
            }
        )
        val order = Order(LocalDate.of(2023, 12, 24), orderItems, appliedPolicies)

        // When
        val discountedPrice = order.totalDiscountedPrice

        // Then
        assertThat(discountedPrice).isEqualTo(Price.from(69_000))
    }
}