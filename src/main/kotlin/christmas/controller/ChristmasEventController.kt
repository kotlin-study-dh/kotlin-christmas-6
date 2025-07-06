package christmas.controller

import christmas.badge.Badge
import christmas.event.ChristmasEvent
import christmas.event.DDayDiscountEvent
import christmas.event.EventSignature
import christmas.event.GiveawayEvent
import christmas.event.SpecialDiscountEvent
import christmas.event.WeekdayDiscountEvent
import christmas.event.WeekendDiscountEvent
import christmas.menu.Order
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

        val order = order()
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

    private fun order(): Order {
        val visitDate = retryOnFailure {
            val visitDay = InputView.readVisitDay()
            runCatching {
                LocalDate.of(2023, 12, visitDay)
            }.getOrElse {
                throw IllegalArgumentException("2023-12-$visitDay is not a valid date.")
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
}
