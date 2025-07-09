package christmas.domain.event.discount

import christmas.domain.order.Menu
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ChristmasDdayDiscountPolicyTest {

    @Test
    fun `evaluate eligible when the order is placed on Christmas D-Day`() {
        val date = LocalDate.of(2023, 12, 25)
        val orderItems = mapOf(Menu.MUSHROOM_SOUP to 1, Menu.T_BONE_STEAK to 1)

        val actual = ChristmasDdayDiscountPolicy.isEligibleFor(date, orderItems)

        assertThat(actual).isTrue()
    }

    @Test
    fun `evaluate eligible when the order is placed before Christmas D-Day`() {
        val placedDate = LocalDate.of(2023, 12, 15)
        val orderItems = mapOf(Menu.MUSHROOM_SOUP to 1, Menu.T_BONE_STEAK to 2)

        val actual = ChristmasDdayDiscountPolicy.isEligibleFor(placedDate, orderItems)

        assertThat(actual).isTrue()
    }

    @Test
    fun `evaluate ineligible when the order is placed after Christmas D-Day`() {
        val placedDate = LocalDate.of(2023, 12, 26)
        val orderItems = mapOf(Menu.MUSHROOM_SOUP to 1, Menu.T_BONE_STEAK to 1)

        val actual = ChristmasDdayDiscountPolicy.isEligibleFor(placedDate, orderItems)

        assertThat(actual).isFalse()
    }
}