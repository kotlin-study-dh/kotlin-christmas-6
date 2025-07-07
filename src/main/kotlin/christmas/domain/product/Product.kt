package christmas.domain.product

import christmas.domain.Money

data class Product(
    val productType: ProductType,
    val name: String,
    val price: Money,
)
