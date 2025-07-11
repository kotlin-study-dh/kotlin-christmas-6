package christmas.domain.order

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.product.Product
import christmas.domain.product.ProductType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.math.BigDecimal

class OrderTest {
    companion object {
        private val product = Product(ProductType.MAIN, "Steak", Money(BigDecimal.valueOf(33.33), Currency.EUR))
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0])
    fun `should order at least 1 amount of product`(amount: Int) {
        assertThrows<IllegalArgumentException> { Order(product, amount) }
    }

    @Test
    fun `should return correct order price`() {
        val product = Product(ProductType.MAIN, "Steak", Money(BigDecimal.valueOf(33.33), Currency.EUR))
        val order = Order(product, 3)

        assert(order.orderPrice() == Money(BigDecimal.valueOf(99.99), Currency.EUR))
    }

    @Test
    fun currency() {
        val product = Product(ProductType.MAIN, "Steak", Money(BigDecimal.valueOf(33.33), Currency.EUR))
        val order = Order(product, 3)

        assert(order.currency() == Currency.EUR)
    }
}
