package christmas.domain.order

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.product.Product
import christmas.domain.product.ProductType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
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

    @Test
    fun `orders can not contain only DRINKs`() {
        val drink1 = Product(ProductType.DRINK, "juice", Money(BigDecimal(2.00), Currency.EUR))
        val drink2 = Product(ProductType.DRINK, "coke", Money(BigDecimal(1.00), Currency.EUR))

        val order1 = Order(drink1, 1)
        val order2 = Order(drink2, 2)

        assertThrows<IllegalArgumentException> { Orders(listOf(order1, order2)) }
    }

    @Test
    fun `success when order amount is 20`() {
        val product1 = Product(ProductType.MAIN, "steak", Money(BigDecimal(10.99), Currency.EUR))
        val product2 = Product(ProductType.DRINK, "juice", Money(BigDecimal(2.00), Currency.EUR))

        val order1 = Order(product1, 10)
        val order2 = Order(product2, 10)

        assertDoesNotThrow { Orders(listOf(order1, order2)) }
    }

    @Test
    fun `fail when order amount is 21`() {
        val product1 = Product(ProductType.MAIN, "steak", Money(BigDecimal(10.99), Currency.EUR))
        val product2 = Product(ProductType.DRINK, "juice", Money(BigDecimal(2.00), Currency.EUR))
        val product3 = Product(ProductType.DRINK, "coke", Money(BigDecimal(1.00), Currency.EUR))

        val order1 = Order(product1, 10)
        val order2 = Order(product2, 10)
        val order3 = Order(product3, 1)

        assertThrows<IllegalArgumentException> { Orders(listOf(order1, order2, order3)) }
    }
}
