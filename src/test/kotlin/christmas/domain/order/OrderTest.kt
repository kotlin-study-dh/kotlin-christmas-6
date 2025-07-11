package christmas.domain.order

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.product.Product
import christmas.domain.product.ProductType
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class OrderTest {
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
