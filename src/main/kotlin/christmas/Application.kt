package christmas

import christmas.domain.view.InputView

fun main() {
    val visitDate = InputView.readVisitDate()
    val orders = InputView.readProductsToOrder()
}
