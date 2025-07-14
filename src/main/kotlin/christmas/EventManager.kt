package christmas

import christmas.domain.Date
import christmas.domain.Order
import christmas.view.InputView
import christmas.view.OutputView

class EventManager {

    fun start() {
        OutputView.printWelcomeMessage()
        val date = getDate()
        val order = getOrder(date)
        OutputView.printEventBenefits(order)
    }

    private fun getDate() =
        retryLogic {
            Date(InputView.readDate())
        }

    private fun getOrder(date: Date) =
        retryLogic {
            Order(InputView.readMenu(), date)
        }


    private inline fun <T> retryLogic(crossinline block: () -> T): T {
        while (true) {
            try {
                return block()
            } catch (e: Exception) {
                println("[ERROR] ${e.message}")
            }
        }
    }
}
