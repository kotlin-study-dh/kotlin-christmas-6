package christmas

import christmas.service.OrderService
import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    val visitDate = InputView.readVisitDate()
    val ordersDto = InputView.readProductsToOrder()
    val orders = OrderService.order(ordersDto)

    OutputView.printEventDescription(12, visitDate)
    OutputView.printOrderDetails(orders)
    OutputView.printTotalPriceBeforeDiscount(orders.totalPrice())
}
