package christmas.configuration.promotion

import christmas.domain.order.Orders
import christmas.domain.promotion.PromotionTest
import christmas.domain.promotion.PromotionTest.Companion.appetizerOrder
import christmas.domain.promotion.PromotionTest.Companion.dessertOrder
import christmas.domain.promotion.PromotionTest.Companion.drinkOrder
import christmas.domain.promotion.PromotionTest.Companion.mainOrder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PromotionsTest : PromotionTest {
    @Test
    fun `should find applied promotions - d-day, weekday(sunday), star day`() {
        val orders = Orders(
            date = LocalDate.of(2023, 12, 10),
            orders = listOf(
                appetizerOrder,
                mainOrder,
                dessertOrder,
                drinkOrder,
            )
        )

        val promotions = Promotions()

        assertThat(promotions.findAppliedPromotions(orders))
            .containsExactlyInAnyOrder(
                StarDiscount,
                WeekdayDessertDiscount,
                ChristmasDdayDiscount,
            )
    }
}
