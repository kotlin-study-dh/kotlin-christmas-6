package christmas.event

import christmas.menu.Menu
import christmas.menu.Order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class WeekdayDiscountEventTest {
    private val event = WeekdayDiscountEvent
    private val weekday = LocalDate.of(2023, 12, 4)

    @Test
    fun `calculates discount amount for dessert menus in a weekday order`() {
        // given
        val menuAndCount = mapOf(
            Menu.TAPAS to 2,
            Menu.T_BONE_STAKE to 1,
            Menu.CHOCOLATE_CAKE to 3
        )
        val order = Order(menuAndCount, weekday)

        // when
        val discount = event.calculateDiscountAmount(order)

        // then
        assertThat(discount).isEqualTo(6_069)
    }

    @Test
    fun `does not calculate discount amount when order amount is below minimum`() {
        // given
        val menuAndCount = mapOf(
            Menu.TAPAS to 1,
        )
        val order = Order(menuAndCount, weekday)

        // when
        val discount = event.calculateDiscountAmount(order)

        // then
        assertThat(discount).isEqualTo(0)
    }

    @Test
    fun `does not calculate discount amount when order is on a weekend`() {
        // given
        val weekend = LocalDate.of(2023, 12, 2)
        val menuAndCount = mapOf(
            Menu.T_BONE_STAKE to 1,
            Menu.CHOCOLATE_CAKE to 3
        )
        val order = Order(menuAndCount, weekend)

        // when
        val discount = event.calculateDiscountAmount(order)

        // then
        assertThat(discount).isEqualTo(0)
    }

    @Test
    fun `does not calculate discount amount when there are no dessert menus`() {
        // given
        val menuAndCount = mapOf(
            Menu.TAPAS to 2,
            Menu.T_BONE_STAKE to 1
        )
        val order = Order(menuAndCount, weekday)

        // when
        val discount = event.calculateDiscountAmount(order)

        // then
        assertThat(discount).isEqualTo(0)
    }
}
