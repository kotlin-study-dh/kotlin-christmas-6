package christmas.policy

import christmas.domain.Menu
import christmas.domain.Order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GiftEventTest {

    @Test
    fun `determine gift amount - over than standard`() {
        val order = Order(
            mapOf(
                Menu.BARBECUE_RIBS to 10
            ), 1
        )
        assertThat(GiftEvent.calculateDiscount(order)).isEqualTo(25000)
    }

    @Test
    fun `determine gift amount - less than standard`() {
        val order = Order(
            mapOf(
                Menu.ZERO_COKE to 1
            ), 1
        )
        assertThat(GiftEvent.calculateDiscount(order)).isEqualTo(0)
    }
}
