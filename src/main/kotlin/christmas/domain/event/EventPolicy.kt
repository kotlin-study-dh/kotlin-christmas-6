package christmas.domain.event

import christmas.domain.Menu
import christmas.domain.Price
import christmas.domain.order.OrderContext

sealed interface EventPolicy {
    val name: String
    fun isEligibleFor(orderContext: OrderContext): Boolean
    fun getBenefitAmount(orderContext: OrderContext): Price
}

object AllEventPolicies {
    val values: List<EventPolicy> = AllDiscountPolicies.values + AllGiftPolicies.values
}

sealed interface DiscountPolicy: EventPolicy {
}


object AllDiscountPolicies {
    val values: List<DiscountPolicy> = listOf(
        ChristmasDdayDiscountPolicy,
        WeekdayDiscountPolicy,
        WeekendDiscountPolicy,
        SpecialDiscountPolicy,
    )
}

sealed interface GiftPolicy: EventPolicy {
    fun getGiftFor(orderContext: OrderContext): Pair<Menu, Int>
}

object AllGiftPolicies {
    val values: List<GiftPolicy> = listOf(
        ChampagneGiftEventPolicy,
    )
}