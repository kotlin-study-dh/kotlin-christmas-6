package christmas

import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    val order = InputView.readOrder()
    OutputView.printBenefit(order)
}
