package christmas.domain

import christmas.policy.ChristmasDDayDiscount
import christmas.policy.GiftEvent
import christmas.policy.SpecialDiscount
import christmas.policy.WeeklyDiscount

object Discount {

    private val discountPolicy = listOf(
        ChristmasDDayDiscount,
        WeeklyDiscount,
        SpecialDiscount,
        GiftEvent
    )

    fun calculateTotalDiscount(order: Order) =
        discountPolicy.sumOf { it.calculateDiscount(order) }
}
