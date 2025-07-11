package christmas.domain.event

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Order
import christmas.domain.product.Product
import christmas.domain.product.ProductType

interface EventTest {
    companion object {
        private val product1 = Product(ProductType.APPETIZER, "salad", Money.longValueOf(10000, Currency.KRW))
        private val product2 = Product(ProductType.MAIN, "steak", Money.longValueOf(20000, Currency.KRW))
        private val product3 = Product(ProductType.DRINK, "coke", Money.longValueOf(1000, Currency.KRW))

        val order1 = Order(product1, 1)
        val order2 = Order(product2, 2)
        val order3 = Order(product3, 3)
    }
}
