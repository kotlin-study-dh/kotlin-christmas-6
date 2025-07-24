package christmas.domain.event.badge

import christmas.domain.order.Menu
import christmas.domain.order.Order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DecemberEventBadgeTest {

    @Test
    fun `get none badge when total benefit is zero`() {
        val order = Order.of(LocalDate.of(2023, 12, 1), mapOf(Menu.MUSHROOM_SOUP to 1))

        val actual = DecemberEventBadge.getBadgeFor(order)

        assertThat(actual).isEqualTo(DecemberEventBadge.NONE)
    }

    @Test
    fun `get star badge when total benefit is 5000`() {
        val order = Order.of(LocalDate.of(2023, 12, 25), mapOf(Menu.ICE_CREAM to 2))

        val actual = DecemberEventBadge.getBadgeFor(order)

        assertThat(actual).isEqualTo(DecemberEventBadge.STAR)
    }

    @Test
    fun `get tree badge when total benefit is 10000`() {
        val order = Order.of(LocalDate.of(2023, 12, 24), mapOf(Menu.ICE_CREAM to 3))

        val actual = DecemberEventBadge.getBadgeFor(order)

        assertThat(actual).isEqualTo(DecemberEventBadge.TREE)
    }

    @Test
    fun `get santa badge when total benefit is 20000`() {
        val order = Order.of(LocalDate.of(2023, 12, 25), mapOf(Menu.T_BONE_STEAK to 3))

        val actual = DecemberEventBadge.getBadgeFor(order)

        assertThat(actual).isEqualTo(DecemberEventBadge.SANTA)
    }
} 