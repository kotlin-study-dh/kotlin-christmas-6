package christmas.domain.promotion

import christmas.domain.promotion.PromotionTest.Companion.appetizerOrder
import christmas.domain.promotion.PromotionTest.Companion.drinkOrder
import christmas.domain.promotion.PromotionTest.Companion.mainOrder
import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
import christmas.domain.promotion.strategy.ChristmasDdayDiscount
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDate

class ChristmasDdayDiscountTest : PromotionTest {

    @ParameterizedTest
    @CsvSource("1,1000", "25,3400", "26,0")
    fun discountAmount(day: Int, amount: Long) {
        // given
        val orders = Orders(LocalDate.of(2023, 12, day), listOf(appetizerOrder, mainOrder, drinkOrder))
        val event = ChristmasDdayDiscount

        // when
        val discountAmount = event.discountAmount(orders)

        // then
        assert(discountAmount == Money.longValueOf(amount, Currency.KRW))
    }

    @Test
    fun giveawayProduct() {
        // given
        val orders = Orders(LocalDate.of(2023, 12, 1), listOf(appetizerOrder, mainOrder, drinkOrder))
        val event = ChristmasDdayDiscount

        // when
        val giveaway = event.giveawayProduct(orders)

        // then
        assert(giveaway == null)
    }
}
