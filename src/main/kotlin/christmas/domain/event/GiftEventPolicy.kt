package christmas.domain.event

import christmas.domain.order.Order


object GiftEventPolicy: EventPolicy {
    private const val MIN_TOTAL_PRICE_FOR_GIFT = 120_000

    override fun isEligibleFor(order: Order): Boolean
     = order.totalPlacedPrice.value >= MIN_TOTAL_PRICE_FOR_GIFT

    fun getGiftFor(order: Order): Pair<String, Int> {
        require(isEligibleFor(order)) {
            "This order is not eligible for this event."
        }
        return "champagne" to 1
    }
}