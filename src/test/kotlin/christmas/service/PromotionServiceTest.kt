package christmas.service

import christmas.domain.order.Orders
import christmas.domain.promotion.PromotionTest
import christmas.domain.promotion.strategy.ChristmasDdayDiscount
import christmas.domain.promotion.strategy.StarDiscount
import christmas.domain.promotion.strategy.WeekdayDessertDiscount
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PromotionServiceTest : PromotionTest {
    val promotionService = PromotionService

    @Test
    fun `should find applied promotions - d-day, weekday(sunday), star day`() {
        val orders = Orders(
            date = LocalDate.of(2023, 12, 10),
            orders = listOf(
                PromotionTest.Companion.appetizerOrder,
                PromotionTest.Companion.mainOrder,
                PromotionTest.Companion.dessertOrder,
                PromotionTest.Companion.drinkOrder,
            )
        )

        Assertions.assertThat(promotionService.findPromotionBenefits(orders).map { it.type })
            .containsExactlyInAnyOrder(
                ChristmasDdayDiscount,
                WeekdayDessertDiscount,
                StarDiscount,
            )
    }
}
