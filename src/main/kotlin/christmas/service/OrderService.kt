package christmas.service

import christmas.domain.order.Order
import christmas.domain.order.Orders
import christmas.repository.InMemoryProductRepository
import christmas.repository.ProductRepository
import christmas.view.CreateOrderDto

object OrderService {
    private val productRepository: ProductRepository = InMemoryProductRepository

    fun order(orders: List<CreateOrderDto>): Orders {
        return Orders(orders.map {
            Order(
                productRepository.findByName(it.name),
                it.amount,
            )
        })
    }
}
