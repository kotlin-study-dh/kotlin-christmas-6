package christmas.repository

import christmas.domain.product.Product

interface ProductRepository {
    fun findByName(name: String): Product
}
