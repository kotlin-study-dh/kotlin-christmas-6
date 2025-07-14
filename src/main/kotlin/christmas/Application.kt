package christmas

import christmas.service.OrderService
import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    val visitDate = InputView.readVisitDate()
    val ordersDto = InputView.readProductsToOrder()
    val orders = OrderService.order(visitDate, ordersDto)

    OutputView.printEventDescription(12, visitDate)
    OutputView.printOrderDetails(orders)
    OutputView.printTotalPriceBeforeDiscount(orders.totalPrice())

    val promotionDetails = OrderService.getPromotionDetails(orders)
    OutputView.printPromotionDetails(promotionDetails, orders)
    OutputView.printEstimatedPriceToPay(promotionDetails, orders)
}
