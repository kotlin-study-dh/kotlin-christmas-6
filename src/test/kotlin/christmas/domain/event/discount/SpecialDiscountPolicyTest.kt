package christmas.domain.event.discount

import christmas.domain.order.Menu
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class SpecialDiscountPolicyTest {

    @Test
    fun `evaluate eligible when the order is placed on special day`() {
        val placedDate = LocalDate.of(2023, 12, 25)
        val orderItems = mapOf(Menu.MUSHROOM_SOUP to 1, Menu.T_BONE_STEAK to 1)

        val actual = SpecialDiscountPolicy.isEligibleFor(placedDate, orderItems)

        assertThat(actual).isTrue()
    }

    @Test
    fun `evaluate ineligible when the order is placed on non-special day`() {
        val placedDate = LocalDate.of(2023, 12, 26)
        val orderItems = mapOf(Menu.MUSHROOM_SOUP to 1, Menu.T_BONE_STEAK to 1)

        val actual = SpecialDiscountPolicy.isEligibleFor(placedDate, orderItems)

        assertThat(actual).isFalse()
    }
}