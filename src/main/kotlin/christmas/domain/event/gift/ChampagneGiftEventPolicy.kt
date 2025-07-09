package christmas.domain.event.gift

import christmas.domain.Menu
import christmas.domain.Price
import christmas.domain.event.EventPolicy
import christmas.domain.order.OrderContext

object ChampagneGiftEventPolicy: GiftPolicy() {
    override val name = "Champagne Gift Event"
    private const val MIN_TOTAL_PRICE_FOR_GIFT = 120_000

    override fun isEligibleForMinimumPrice(orderContext: OrderContext): Boolean {
        return orderContext.totalPrice.value >= MIN_TOTAL_PRICE_FOR_GIFT
    }

    override fun checkSpecificEventConditions(orderContext: OrderContext): Boolean {
        return true
    }

    override fun getBenefitAmount(orderContext: OrderContext): Price {
        return Menu.CHAMPAGNE.price
    }

    override fun getGiftFor(orderContext: OrderContext): Pair<Menu, Int> {
        require(EventPolicy.isEligibleFor(orderContext)) {
            "This order is not eligible for this event."
        }
        return Menu.CHAMPAGNE to 1
    }
}