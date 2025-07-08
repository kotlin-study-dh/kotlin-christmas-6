package christmas.policy

import christmas.domain.Menu
import christmas.domain.Order

object GiftEvent: DiscountPolicy {

    private const val CHAMPAGNE_GIFT_STANDARD = 120_000

    override fun calculateDiscount(order: Order) =
        if (order.totalPrice >= CHAMPAGNE_GIFT_STANDARD) Menu.CHAMPAGNE.price else 0
}