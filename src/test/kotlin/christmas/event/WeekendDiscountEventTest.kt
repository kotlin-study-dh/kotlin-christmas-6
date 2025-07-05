package christmas.event

import christmas.menu.Menu
import christmas.menu.Order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class WeekendDiscountEventTest {
    private val event = WeekendDiscountEvent
    private val weekend = LocalDate.of(2023, 12, 2)

    @Test
    fun `calculates discount amount for main course menus in a weekend order`() {
        // given
        val menuAndCount = mapOf(
            Menu.T_BONE_STAKE to 2,
            Menu.CHOCOLATE_CAKE to 1
        )
        val order = Order(menuAndCount, weekend)

        // when
        val discount = event.calculateDiscountAmount(order)

        // then
        assertThat(discount).isEqualTo(4_046)
    }

    @Test
    fun `does not calculate discount amount when order amount is below minimum`() {
        // given
        val menuAndCount = mapOf(
            Menu.TAPAS to 1,
        )
        val order = Order(menuAndCount, weekend)

        // when
        val discount = event.calculateDiscountAmount(order)

        // then
        assertThat(discount).isEqualTo(0)
    }

    @Test
    fun `does not calculate discount amount when order is on a weekday`() {
        // given
        val weekday = LocalDate.of(2023, 12, 4)
        val menuAndCount = mapOf(
            Menu.T_BONE_STAKE to 1,
            Menu.CHOCOLATE_CAKE to 3
        )
        val order = Order(menuAndCount, weekday)

        // when
        val discount = event.calculateDiscountAmount(order)

        // then
        assertThat(discount).isEqualTo(0)
    }

    @Test
    fun `does not calculate discount amount when no main course menus are ordered`() {
        // given
        val menuAndCount = mapOf(
            Menu.CHOCOLATE_CAKE to 2,
            Menu.ZERO_COKE to 1
        )
        val order = Order(menuAndCount, weekend)

        // when
        val discount = event.calculateDiscountAmount(order)

        // then
        assertThat(discount).isEqualTo(0)
    }
}
