package christmas

import christmas.service.OrderService
import christmas.view.InputView

fun main() {
    val visitDate = InputView.readVisitDate()
    val ordersDto = InputView.readProductsToOrder()
    val orders = OrderService.order(ordersDto)
}
