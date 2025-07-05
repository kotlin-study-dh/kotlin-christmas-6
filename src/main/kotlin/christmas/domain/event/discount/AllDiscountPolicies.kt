package christmas.domain.event.discount

object AllDiscountPolicies {
    val values: List<DiscountPolicy> = listOf(
        ChristmasDdayDiscountPolicy,
        WeekdayDiscountPolicy,
        WeekendDiscountPolicy,
        SpecialDiscountPolicy,
    )
}