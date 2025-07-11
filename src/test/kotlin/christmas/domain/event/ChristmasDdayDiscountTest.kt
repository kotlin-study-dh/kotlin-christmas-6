package christmas.domain.event

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Order
import christmas.domain.order.Orders
import christmas.domain.product.Product
import christmas.domain.product.ProductType
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.time.LocalDate

class ChristmasDdayDiscountTest {
    companion object {
        private val product1 = Product(ProductType.APPETIZER, "salad", Money.longValueOf(10000, Currency.KRW))
        private val product2 = Product(ProductType.MAIN, "steak", Money.longValueOf(20000, Currency.KRW))
        private val product3 = Product(ProductType.DRINK, "coke", Money.longValueOf(1000, Currency.KRW))

        private val order1 = Order(product1, 1)
        private val order2 = Order(product2, 2)
        private val order3 = Order(product3, 3)
    }

    @ParameterizedTest
    @CsvSource("1,1000", "25,3400", "26,0")
    fun discountAmount(day: Int, amount: Long) {
        // given
        val orders = Orders(LocalDate.of(2023, 12, day), listOf(order1, order2, order3))
        val event = ChristmasDdayDiscount()

        // when
        val discountAmount = event.discountAmount(orders)

        // then
        assert(discountAmount == Money.longValueOf(amount, Currency.KRW))
    }

    @Test
    fun giveawayProduct() {
        // given
        val orders = Orders(LocalDate.of(2023, 12, 1), listOf(order1, order2, order3))
        val event = ChristmasDdayDiscount()

        // when
        val giveaway = event.giveawayProduct(orders)

        // then
        assert(giveaway == null)
    }
}
