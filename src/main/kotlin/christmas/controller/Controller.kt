package christmas.controller

import christmas.domain.Badge
import christmas.domain.Reception
import christmas.domain.menu.Money
import christmas.ui.Input
import christmas.ui.Output

typealias Bill = List<Pair<String, Long>>

object Controller {

    fun control() {
        val date = ErrorHandler.handle {
            Input.enterReservationDate()
        }

        val result = handleRequest(date)
        val badge = Badge.from(Money(result.discountAmount))
        Output.printMenuSummary(result.menus)
        Output.printPreDiscountAmount(result.purchaseAmount)
        Output.printPresent(result.reception.hasPresent())
        Output.printDiscountDetails(createBill(result.reception))
        Output.printTotalDiscount(result.discountAmount)
        Output.printNetPrice(result.reception.aggregateNetPrice())
        Output.printBadge(badge.title)
    }

    private fun handleRequest(reservationDate: Int): ReceptionResult {
        val result = ErrorHandler.handle {
            val menus = Input.enterMenus()
            val reception = Reception(reservationDate, menus)
            val purchaseAmount = reception.aggregatePurchaseAmount()
            val discountAmount = reception.aggregateTotalDiscount()
            ReceptionResult(menus, reception, purchaseAmount, discountAmount, reservationDate)
        }
        return result
    }

    private fun createBill(reception: Reception): Bill {
        return listOf(
            Pair("크리스마스 디데이 할인", reception.applyDdayDiscount()),
            Pair("평일 할인", reception.applyWeekdayDiscount()),
            Pair("주말 할인", reception.applyWeekendDiscount()),
            Pair("특별 할인", reception.applySpecialDiscount()),
            Pair("증정 할인", reception.applyPresentDiscount())
        ).filter { it.second != 0L }
    }
}