package christmas.event

import christmas.menu.Menu
import christmas.menu.Order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class GiveawayEventTest {
    private val event = GiveawayEvent
    private val defaultDate = LocalDate.of(2023, 12, 31)

    @Test
    fun `calculates giveaway amount for orders above minimum amount`() {
        // given
        val menuAndCounts = mapOf(
            Menu.T_BONE_STAKE to 2,
            Menu.BARBECUE_RIBS to 1,
            Menu.CHOCOLATE_CAKE to 2
        )
        val order = Order(menuAndCounts, defaultDate)

        // when
        val giveawayAmount = GiveawayEvent.calculateBenefitAmount(order)

        // then
        assertThat(giveawayAmount).isEqualTo(Menu.CHAMPAGNE.price)
    }

    @Test
    fun `does not calculate giveaway amount when order amount is below minimum`() {
        // given
        val menuAndCounts = mapOf(
            Menu.CHRISTMAS_PASTA to 4,
            Menu.CHOCOLATE_CAKE to 1,
            Menu.ZERO_COKE to 1
        )
        val order = Order(menuAndCounts, defaultDate)

        // when
        val giveawayAmount = GiveawayEvent.calculateBenefitAmount(order)

        // then
        assertThat(giveawayAmount).isEqualTo(0)
    }
}
