package christmas.view

import christmas.domain.Badge
import christmas.domain.DiscountCalculator
import christmas.domain.Menu
import christmas.domain.Order
import christmas.policy.ChristmasDDayDiscount
import christmas.policy.GiftEvent
import christmas.policy.SpecialDiscount
import christmas.policy.WeeklyDiscount
import java.time.DayOfWeek

object OutputView {

    fun printWelcomeMessage() = println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")

    fun printEventBenefits(order: Order) {
        printDateMessage(order)
        printOrderMenu(order)
        printTotalDiscount(order)
        printGift(order)
        printBenefits(order)
        printTotalBenefitsPrice(order)
        printEstimatedPayment(order)
        printBadge(order)
    }

    private fun printDateMessage(order: Order) =
        println("12월 ${order.date}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n")

    private fun printOrderMenu(order: Order) {
        val orderMenu = order.menus.entries.joinToString("\n") { (menu, quantity) ->
            "${menu.itemName} ${quantity}개"
        }
        print("<주문 메뉴>\n$orderMenu\n\n")
    }

    private fun printTotalDiscount(order: Order) {
        println("<할인 전 총주문 금액>\n${String.format("%,d", order.totalPrice)}원\n")
    }

    private fun printGift(order: Order) {
        val gift = if (GiftEvent.isEligible(order)) "${Menu.CHAMPAGNE.itemName} 1개" else "없음"
        println("<증정 메뉴>\n$gift\n")
    }

    private fun printBenefits(order: Order) {
        println("<혜택 내역>")
        println(discountList(order))
    }

    private fun printTotalBenefitsPrice(order: Order) {
        val totalBenefitAmount = DiscountCalculator.calculateTotalBenefitAmount(order)
        val prefix = if (totalBenefitAmount > 0) "-" else ""
        println("<총혜택 금액>\n${prefix}${String.format("%,d", totalBenefitAmount)}원\n")
    }

    private fun discountList(order: Order): String {
        var discounts = ""
        if (ChristmasDDayDiscount.calculateDiscount(order) > 0) {
            discounts = discounts.plus(
                "크리스마스 디데이 할인: -${
                    String.format(
                        "%,d",
                        ChristmasDDayDiscount.calculateDiscount(order)
                    )
                }원\n"
            )
        }
        if (WeeklyDiscount.calculateDiscount(order) > 0) {
            when (order.getDayOfWeek()) {
                DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY
                    -> discounts =
                    discounts.plus("평일 할인: -${String.format("%,d", WeeklyDiscount.calculateDiscount(order))}원\n")

                DayOfWeek.FRIDAY, DayOfWeek.SATURDAY
                    -> discounts =
                    discounts.plus("주말 할인: -${String.format("%,d", WeeklyDiscount.calculateDiscount(order))}원\n")
            }
        }
        if (SpecialDiscount.calculateDiscount(order) > 0) {
            discounts = discounts.plus("특별 할인: -${String.format("%,d", SpecialDiscount.calculateDiscount(order))}원\n")
        }
        if (GiftEvent.calculateDiscount(order) > 0) {
            discounts = discounts.plus("증정 이벤트: -${String.format("%,d", GiftEvent.calculateDiscount(order))}원\n")
        }
        if (discounts == "") discounts = discounts.plus("없음\n")
        return discounts
    }

    private fun printEstimatedPayment(order: Order) =
        println("<할인 후 예상 결제 금액>\n${String.format("%,d", DiscountCalculator.calculateTotalPayment(order))}원\n")

    private fun printBadge(order: Order) =
        println("<12월 이벤트 배지>\n${Badge.choose(DiscountCalculator.calculateTotalBenefitAmount(order)).itemName}")
}
