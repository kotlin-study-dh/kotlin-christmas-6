package christmas.domain.event.discount

import christmas.domain.order.Menu
import christmas.domain.order.Price
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class WeekdayDiscountPolicyTest {

    @Test
    fun `evaluate eligible when the order is placed on weekday`() {
        val weekday = LocalDate.of(2023, 12, 4)
        val orderItems = mapOf(Menu.TAPAS to 3)

        val actual = WeekdayDiscountPolicy.isEligibleFor(weekday, orderItems)

        assertThat(actual).isTrue()
    }

    @Test
    fun `evaluate ineligible when the order is placed on weekend`() {
        val weekend = LocalDate.of(2023, 12, 2)
        val orderItems = mapOf(Menu.TAPAS to 3)

        val actual = WeekdayDiscountPolicy.isEligibleFor(weekend, orderItems)

        assertThat(actual).isFalse()
    }

    @Test
    fun `calculate discount amount for dessert menu items`() {
        val weekday = LocalDate.of(2023, 12, 4)
        val orderItems = mapOf(Menu.CHOCOLATE_CAKE to 2, Menu.ICE_CREAM to 1)

        val discountAmount = WeekdayDiscountPolicy.getBenefitAmount(weekday, orderItems)

        assertThat(discountAmount).isEqualTo(Price.from(6_069))
    }
}