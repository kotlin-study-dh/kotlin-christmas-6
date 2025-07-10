package christmas

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.service.OrderService
import christmas.view.InputView
import christmas.view.OutputView
import java.math.BigDecimal

fun main() {
    val visitDate = InputView.readVisitDate()
    val ordersDto = InputView.readProductsToOrder()
    val orders = OrderService.order(ordersDto)

    OutputView.printEventDescription(12, visitDate)
    OutputView.printOrderDetails(orders)
    OutputView.printTotalPriceBeforeDiscount(Money(BigDecimal.ZERO, Currency.KRW))
}
