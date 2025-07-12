package christmas.view

import christmas.badge.Badge
import christmas.event.EventSignature
import christmas.menu.Menu
import christmas.menu.Order
import java.text.DecimalFormat

object OutputView {
    private const val NO_BENEFIT_MESSAGE = "없음"
    private const val ERROR_MESSAGE_PREFIX = "[ERROR] "
    private val numberFormatter = DecimalFormat("#,###")
    private val eventSignatureNames = mapOf(
        EventSignature.WEEKDAY_DISCOUNT to "평일 할인",
        EventSignature.WEEKEND_DISCOUNT to "주말 할인",
        EventSignature.SPECIAL_DISCOUNT to "특별 할인",
        EventSignature.D_DAY_DISCOUNT to "크리스마스 디데이 할인",
        EventSignature.GIVEAWAY to "증정 이벤트"
    )

    fun printWelcomeMessage() {
        println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.")
    }

    fun printEventPreviewMessage(day: Int) {
        println("12월 ${day}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
    }

    fun printOrderSummary(order: Order) {
        println(System.lineSeparator() + "<주문 메뉴>")
        order.menuAndCounts.forEach { (menu, count) ->
            println("${menu.korName} - ${count}개")
        }

        println(System.lineSeparator() + "<할인 전 총주문 금액>")
        println("${numberFormatter.format(order.totalOrderAmount)}원")
    }

    fun printGiveawayMenus(giveawayMenus: Map<Menu, Int>) {
        println(System.lineSeparator() + "<증정 메뉴>")
        if (giveawayMenus.isEmpty()) {
            println(NO_BENEFIT_MESSAGE)
            return
        }
        giveawayMenus.forEach { println("${it.key.korName} - ${it.value}개") }
    }

    fun printBenefitSummary(benefitAmounts: Map<EventSignature, Int>) {
        println(System.lineSeparator() + "<혜택 내역>")
        benefitAmounts.filter { it.value > 0 }
            .also { filteredBenefits ->
                if (filteredBenefits.isEmpty()) {
                    println(NO_BENEFIT_MESSAGE)
                }
            }
            .forEach { (eventSignature, amount) ->
                println("${eventSignatureNames[eventSignature]}: -${numberFormatter.format(amount)}원")
            }
        printTotalBenefitAmount(benefitAmounts)
    }

    private fun printTotalBenefitAmount(benefitAmounts: Map<EventSignature, Int>) {
        println(System.lineSeparator() + "<총혜택 금액>")
        val totalBenefitAmount = benefitAmounts.values.sum()
        if (totalBenefitAmount == 0) {
            println(NO_BENEFIT_MESSAGE)
            return
        }
        println("-${numberFormatter.format(totalBenefitAmount)}원")
    }

    fun printEstimatedPaymentAmount(order: Order, benefitAmounts: Map<EventSignature, Int>) {
        println(System.lineSeparator() + "<할인 후 예상 결제 금액>")
        val totalOrderAmount = order.totalOrderAmount
        val totalBenefitAmount = benefitAmounts.values.sum()
        val giveawayAmount = benefitAmounts[EventSignature.GIVEAWAY] ?: 0
        val estimatedPaymentAmount = totalOrderAmount - totalBenefitAmount + giveawayAmount
        println("${numberFormatter.format(estimatedPaymentAmount)}원")
    }

    fun printBadge(badge: Badge) {
        println(System.lineSeparator() + "<12월 이벤트 배지>")
        when (badge) {
            Badge.NONE -> println(NO_BENEFIT_MESSAGE)
            Badge.STAR -> println("별")
            Badge.TREE -> println("트리")
            Badge.SANTA -> println("산타")
        }
    }

    fun printErrorMessage(message: String?) {
        println("$ERROR_MESSAGE_PREFIX${message ?: "An unknown error occurred"}")
    }
}
