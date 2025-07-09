package christmas.domain.order

import christmas.domain.Menu
import christmas.domain.Price
import christmas.domain.sum
import java.time.LocalDate

data class OrderContext(
    val placedDate: LocalDate,
    val orderItems: Map<Menu, Int>,
) {
    val totalPrice: Price
        get() = orderItems.map { (menu, amount) -> menu.price times amount }.sum()

    companion object {
        fun from(order: Order) =  OrderContext(
            placedDate = order.placedDate,
            orderItems = order.orderItems,
        )
    }
}