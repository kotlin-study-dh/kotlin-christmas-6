package christmas.controller

import christmas.badge.Badge
import christmas.event.ChristmasEvent
import christmas.event.DDayDiscountEvent
import christmas.event.EventSignature
import christmas.event.GiveawayEvent
import christmas.event.SpecialDiscountEvent
import christmas.event.WeekdayDiscountEvent
import christmas.event.WeekendDiscountEvent
import christmas.order.Order
import christmas.view.InputView
import christmas.view.OutputView
import java.time.LocalDate

class ChristmasEventController {
    private val christmasEvents: List<ChristmasEvent> = listOf(
        WeekdayDiscountEvent,
        WeekendDiscountEvent,
        SpecialDiscountEvent,
        GiveawayEvent,
        DDayDiscountEvent,
    )

    fun start() {
        OutputView.printWelcomeMessage()

        val order = getOrder()
        OutputView.printEventPreviewMessage(order.day)
        OutputView.printOrderSummary(order)

        val benefitAmounts = christmasEvents
            .associate { it.signature() to it.calculateBenefitAmount(order) }

        benefitAmounts[EventSignature.GIVEAWAY]?.takeIf { it > 0 }
            ?.let { OutputView.printGiveawayMenus(GiveawayEvent.giveawayMenus) }
            ?: OutputView.printGiveawayMenus(emptyMap())

        OutputView.printBenefitSummary(benefitAmounts)
        OutputView.printEstimatedPaymentAmount(order, benefitAmounts)

        val badge = Badge.from(benefitAmounts.values.sum())
        OutputView.printBadge(badge)
    }

    private fun getOrder(): Order {
        val visitDate = retryOnFailure {
            val visitDay = InputView.readVisitDay()
            runCatching {
                LocalDate.of(DEFAULT_YEAR, DEFAULT_MONTH, visitDay)
            }.getOrElse {
                throw IllegalArgumentException("$DEFAULT_YEAR-$DEFAULT_MONTH-$visitDay is not a valid date.")
            }
        }
        return retryOnFailure {
            val menuNameAndCounts = InputView.readMenuNameAndCounts()
            Order.of(menuNameAndCounts, visitDate)
        }
    }

    private fun <T> retryOnFailure(block: () -> T): T =
        runCatching<T> { block() }
            .onFailure { e ->
                if (e is IllegalArgumentException) {
                    OutputView.printErrorMessage(e.message)
                    retryOnFailure(block)
                }
            }.getOrThrow()

    companion object {
        private const val DEFAULT_YEAR = 2023
        private const val DEFAULT_MONTH = 12
    }
}
