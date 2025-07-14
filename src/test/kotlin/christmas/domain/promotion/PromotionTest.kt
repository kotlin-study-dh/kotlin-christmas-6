package christmas.domain.promotion

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Order
import christmas.domain.product.Product
import christmas.domain.product.ProductType

interface PromotionTest {
    companion object {
        private val appetizer = Product(ProductType.APPETIZER, "salad", Money.longValueOf(10000, Currency.KRW))
        private val main = Product(ProductType.MAIN, "steak", Money.longValueOf(10000, Currency.KRW))
        private val dessert = Product(ProductType.DESSERT, "cake", Money.longValueOf(10000, Currency.KRW))
        private val drink = Product(ProductType.DRINK, "coke", Money.longValueOf(10000, Currency.KRW))

        val appetizerOrder = Order(appetizer, 2)
        val mainOrder = Order(main, 2)
        val dessertOrder = Order(dessert, 2)
        val drinkOrder = Order(drink, 2)
    }
}
