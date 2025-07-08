package christmas.policy

import christmas.domain.Menu
import christmas.domain.Order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WeeklyDiscountTest {

    @Test
    fun `calculate weekday discount of dessert`() {
        val order = Order(
            mapOf(
                Menu.CHOCOLATE_CAKE to 1,
                Menu.ICE_CREAM to 2,
                Menu.BARBECUE_RIBS to 2
            ), 3
        )
        assertThat(WeeklyDiscount.calculateDiscount(order)).isEqualTo(2023 * 3)
    }

    @Test
    fun `calculate weekday discount of main menu`() {
        val order = Order(
            mapOf(
                Menu.CHOCOLATE_CAKE to 1,
                Menu.ICE_CREAM to 2,
                Menu.BARBECUE_RIBS to 2
            ), 2
        )
        assertThat(WeeklyDiscount.calculateDiscount(order)).isEqualTo(2023 * 2)
    }
}
