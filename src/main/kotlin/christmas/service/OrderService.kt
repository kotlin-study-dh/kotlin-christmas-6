package christmas.service

import christmas.domain.order.Order
import christmas.domain.order.Orders
import christmas.repository.InMemoryProductRepository
import christmas.repository.ProductRepository
import christmas.view.CreateOrderDto
import java.time.LocalDate

object OrderService {
    private val productRepository: ProductRepository = InMemoryProductRepository

    fun order(visitDate: Int, orders: List<CreateOrderDto>): Orders {
        return Orders(
            date = LocalDate.of(2023, 12, visitDate),
            orders = orders.map {
                Order(productRepository.findByName(it.name), it.amount)
            })
    }
}
