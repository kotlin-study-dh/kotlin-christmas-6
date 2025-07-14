package christmas.domain

import christmas.policy.ChristmasDDayDiscount
import christmas.policy.GiftEvent
import christmas.policy.SpecialDiscount
import christmas.policy.WeeklyDiscount

object DiscountCalculator {

    private val discountPolicy = listOf(
        ChristmasDDayDiscount,
        WeeklyDiscount,
        SpecialDiscount,
        GiftEvent
    )

    fun calculateTotalBenefitAmount(order: Order) =
        discountPolicy.sumOf { it.calculateDiscount(order) }

    fun calculateTotalPayment(order: Order) =
        order.totalPrice - calculateTotalDiscountAmount(order)

    fun calculateTotalDiscountAmount(order: Order): Int {
        return discountPolicy
            .filter { it != GiftEvent }
            .sumOf { it.calculateDiscount(order) }
    }
}
