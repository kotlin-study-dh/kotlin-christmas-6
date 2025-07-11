package christmas.domain.order

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.product.Product
import christmas.domain.product.ProductType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

class OrdersTest {
    @Test
    fun `should throw exception when no Order has made`() {
        assertThrows<IllegalArgumentException> { Orders(emptyList()) }
    }

    @Test
    fun totalPrice() {
        val product1 = Product(ProductType.MAIN, "steak", Money(BigDecimal(10.99), Currency.EUR))
        val product2 = Product(ProductType.DRINK, "coke", Money(BigDecimal(1.00), Currency.EUR))

        val order1 = Order(product1, 1)
        val order2 = Order(product2, 2)

        val orders = Orders(listOf(order1, order2))

        assert(orders.totalPrice() == Money(BigDecimal(12.99), Currency.EUR))
    }
}
