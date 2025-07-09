package christmas.domain.event.discount

import christmas.domain.event.EventPolicy

abstract class DiscountPolicy: EventPolicy() {
}

object AllDiscountPolicies {
    val values: List<DiscountPolicy> = listOf(
        ChristmasDdayDiscountPolicy,
        WeekdayDiscountPolicy,
        WeekendDiscountPolicy,
        SpecialDiscountPolicy,
    )
} 