package christmas.domain.event.discount

import christmas.domain.order.Menu
import christmas.domain.order.Price
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class WeekendDiscountPolicyTest {

    @Test
    fun `evaluate eligible when the order is placed on weekend`() {
        val weekend = LocalDate.of(2023, 12, 2)
        val orderItems = mapOf(Menu.TAPAS to 3)

        val actual = WeekendDiscountPolicy.isEligibleFor(weekend, orderItems)

        assertThat(actual).isTrue()
    }

    @Test
    fun `evaluate ineligible when the order is placed on weekday`() {
        val weekday = LocalDate.of(2023, 12, 4)
        val orderItems = mapOf(Menu.TAPAS to 3)

        val actual = WeekendDiscountPolicy.isEligibleFor(weekday, orderItems)

        assertThat(actual).isFalse()
    }

    @Test
    fun `calculate discount amount for main menu items`() {
        val weekend = LocalDate.of(2023, 12, 2)
        val orderItems = mapOf(Menu.T_BONE_STEAK to 2, Menu.BBQ_RIBS to 1)

        val discountAmount = WeekendDiscountPolicy.getBenefitAmount(weekend, orderItems)

        assertThat(discountAmount).isEqualTo(Price.from(6_069))
    }
}