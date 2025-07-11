package christmas.domain.order

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.product.Product

data class Order(
    val product: Product,
    val amount: Int,
) {
    fun orderPrice(): Money {
        return product.price.multiply(amount)
    }

    fun currency(): Currency {
        return product.currency()
    }
}
