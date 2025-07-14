package christmas.domain.product

import christmas.domain.money.Currency
import christmas.domain.money.Money

data class Product(
    val productType: ProductType,
    val name: String,
    val price: Money,
) {
    fun currency(): Currency {
        return price.currency
    }
}
