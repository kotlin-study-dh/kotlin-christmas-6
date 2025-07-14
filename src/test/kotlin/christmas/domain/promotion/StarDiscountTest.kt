package christmas.domain.promotion

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
import christmas.domain.promotion.PromotionTest.Companion.dessertOrder
import christmas.domain.promotion.PromotionTest.Companion.mainOrder
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDate

class StarDiscountTest : PromotionTest {
    private val promotion = StarDiscount

    @ParameterizedTest
    @ValueSource(ints = [3, 10, 17, 24, 25, 31])
    fun `discount on STAR Days`(day: Int) {
        val orders = Orders(LocalDate.of(2023, 12, day), listOf(mainOrder, dessertOrder))
        assert(promotion.discountAmount(orders) == Money.longValueOf(1000, Currency.KRW))
    }

    @Test
    fun `no discount on non-STAR day`() {
        val orders = Orders(LocalDate.of(2023, 12, 1), listOf(mainOrder, dessertOrder))
        assert(promotion.discountAmount(orders) == Money.longValueOf(0, Currency.KRW))
    }
}
