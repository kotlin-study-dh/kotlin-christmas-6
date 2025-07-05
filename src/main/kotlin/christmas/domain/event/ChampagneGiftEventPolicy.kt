package christmas.domain.event

import christmas.domain.Menu
import christmas.domain.Price
import christmas.domain.order.OrderContext


object ChampagneGiftEventPolicy: GiftPolicy {
    override val name = "Champagne Gift Event"
    private const val MIN_TOTAL_PRICE_FOR_GIFT = 120_000

    override fun isEligibleFor(orderContext: OrderContext): Boolean
     = orderContext.totalPrice.value >= MIN_TOTAL_PRICE_FOR_GIFT

    override fun getBenefitAmount(orderContext: OrderContext): Price {
        return Menu.CHAMPAGNE.price
    }

    override fun getGiftFor(orderContext: OrderContext): Pair<Menu, Int> {
        require(isEligibleFor(orderContext)) {
            "This order is not eligible for this event."
        }
        return Menu.CHAMPAGNE to 1
    }
}