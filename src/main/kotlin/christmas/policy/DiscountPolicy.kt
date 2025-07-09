package christmas.policy

import christmas.domain.Order

sealed interface DiscountPolicy {

    fun calculateDiscount(order: Order): Int
}
