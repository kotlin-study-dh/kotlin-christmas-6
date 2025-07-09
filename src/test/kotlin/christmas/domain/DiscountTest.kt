package christmas.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DiscountTest {

    @Test
    fun `calculate total discount amount`() {
        val order = Order(
            mapOf(
                Menu.T_BONE_STEAK to 1,
                Menu.BARBECUE_RIBS to 1,
                Menu.CHOCOLATE_CAKE to 2,
                Menu.ZERO_COKE to 1
            ), 3
        )
        val totalDiscount = Discount.calculateTotalBenefitAmount(order)
        assertThat(totalDiscount).isEqualTo(31_246)
    }

    @Test
    fun `calculate total discount amount - no discount`() {
        val order = Order(
            mapOf(
                Menu.TAPAS to 1,
                Menu.ZERO_COKE to 1
            ), 26
        )
        val totalDiscount = Discount.calculateTotalBenefitAmount(order)
        assertThat(totalDiscount).isEqualTo(0)
    }
}
