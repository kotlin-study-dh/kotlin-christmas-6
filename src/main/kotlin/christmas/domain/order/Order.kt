package christmas.domain.order

import christmas.domain.product.Product

data class Order(
    val product: Product,
    val amount: Int,
)
