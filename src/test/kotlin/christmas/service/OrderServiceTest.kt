package christmas.service

import christmas.view.CreateOrderDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OrderServiceTest {

    @Test
    fun `should create valid order`() {
        val ordersDto = listOf(
            CreateOrderDto("타파스", 2),
            CreateOrderDto("티본스테이크", 3),
        )

        val orders = OrderService.order(ordersDto)

        assertThat(orders.orders.map { it.product.name }).containsOnly("타파스", "티본스테이크")
        assertThat(orders.orders.map { it.amount }).containsOnly(2, 3)
    }
}
