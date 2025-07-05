package christmas.domain.event

import christmas.domain.Price
import christmas.domain.order.OrderContext


object ChampagneGiftEventPolicy: EventPolicy {
    private const val MIN_TOTAL_PRICE_FOR_GIFT = 120_000

    override fun isEligibleFor(orderContext: OrderContext): Boolean
     = orderContext.totalPrice.value >= MIN_TOTAL_PRICE_FOR_GIFT

    override fun getBenefitAmount(orderContext: OrderContext): Price {
        return Price.from(25_000)
    }

    fun getGiftFor(orderContext: OrderContext): Pair<String, Int> {
        require(isEligibleFor(orderContext)) {
            "This order is not eligible for this event."
        }
        return "champagne" to 1
    }
}