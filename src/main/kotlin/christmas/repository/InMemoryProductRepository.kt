package christmas.repository

import christmas.database.ProductDatabase
import christmas.domain.product.Product

object InMemoryProductRepository : ProductRepository {
    override fun findByName(name: String): Product? {
        return ProductDatabase.products
            .find { it.name == name }
    }
}
