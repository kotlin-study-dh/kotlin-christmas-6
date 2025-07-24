package christmas.domain.order

import christmas.domain.event.discount.ChristmasDdayDiscountPolicy
import christmas.domain.event.discount.SpecialDiscountPolicy
import christmas.domain.event.gift.ChampagneGiftEventPolicy
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
            SpecialDiscountPolicy
        )
        val order = Order(LocalDate.of(2023, 12, 24), orderItems, appliedPolicies)

        // When
        val discountedPrice = order.totalDiscountedPrice

        // Then
        assertThat(discountedPrice).isEqualTo(Price.from(69_000))
    }

    @Test
    fun `get applied discount policies`() {
        val order = Order.of(LocalDate.of(2023, 12, 25), mapOf(Menu.T_BONE_STEAK to 1))

        val actual = order.appliedDiscountPolicies

        assertThat(actual).contains(ChristmasDdayDiscountPolicy)
    }

    @Test
    fun `get applied gift policies`() {
        val order = Order.of(LocalDate.of(2023, 12, 25), mapOf(Menu.CHAMPAGNE to 5, Menu.T_BONE_STEAK to 2))

        val actual = order.appliedGiftPolicies

        assertThat(actual).contains(ChampagneGiftEventPolicy)
    }

    @Test
    fun `get total placed price`() {
        val order = Order.of(LocalDate.of(2023, 12, 25), mapOf(Menu.T_BONE_STEAK to 1, Menu.CHOCOLATE_CAKE to 2))

        val actual = order.totalPlacedPrice

        assertThat(actual).isEqualTo(Price.from(85_000))
    }

    @Test
    fun `get total discount amount`() {
        val order = Order.of(LocalDate.of(2023, 12, 25), mapOf(Menu.T_BONE_STEAK to 1))

        val actual = order.totalDiscountAmount

        assertThat(actual).isGreaterThan(Price.from(0))
    }

    @Test
    fun `get total discounted price`() {
        val order = Order.of(LocalDate.of(2023, 12, 25), mapOf(Menu.T_BONE_STEAK to 1))

        val actual = order.totalDiscountedPrice

        assertThat(actual).isLessThan(order.totalPlacedPrice)
    }

    @Test
    fun `get total benefit price`() {
        val order = Order.of(
            LocalDate.of(2023, 12, 25),
            mapOf(Menu.T_BONE_STEAK to 1, Menu.MUSHROOM_SOUP to 5, Menu.CHAMPAGNE to 5)
        )

        val actual = order.totalBenefitPrice

        assertThat(actual).isEqualTo(Price.from(29_400))
    }

    @Test
    fun `get event badge`() {
        val order = Order.of(LocalDate.of(2023, 12, 25), mapOf(Menu.T_BONE_STEAK to 1))

        val actual = order.eventBadge

        assertThat(actual).isNotNull()
    }

    @Test
    fun `get placed date`() {
        val placedDate = LocalDate.of(2023, 12, 25)
        val order = Order.of(placedDate, mapOf(Menu.T_BONE_STEAK to 1))

        val actual = order.placedDate

        assertThat(actual).isEqualTo(placedDate)
    }

    @Test
    fun `get order items`() {
        val orderItems = mapOf(Menu.T_BONE_STEAK to 1, Menu.CHOCOLATE_CAKE to 2)
        val order = Order.of(LocalDate.of(2023, 12, 25), orderItems)

        val actual = order.orderItems

        assertThat(actual).isEqualTo(orderItems)
    }

    @Test
    fun `get applied event policies`() {
        val order = Order.of(LocalDate.of(2023, 12, 25), mapOf(Menu.T_BONE_STEAK to 1))

        val actual = order.appliedEventPolicies

        assertThat(actual).isNotEmpty()
    }
}