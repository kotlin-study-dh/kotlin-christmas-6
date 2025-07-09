package christmas.domain.event.gift

import christmas.domain.order.Menu
import christmas.domain.order.Price
import java.time.LocalDate

object ChampagneGiftEventPolicy: GiftPolicy() {
    override val name = "Champagne Gift Event"
    private const val MIN_TOTAL_PRICE_FOR_GIFT = 120_000

    override fun isEligibleForMinimumPrice(totalPrice: Price): Boolean {
        return totalPrice.value >= MIN_TOTAL_PRICE_FOR_GIFT
    }

    override fun checkSpecificEventConditions(placedDate: LocalDate, orderItems: Map<Menu, Int>): Boolean {
        return true
    }

    override fun getBenefitAmount(placedDate: LocalDate, orderItems: Map<Menu, Int>): Price {
        return Menu.CHAMPAGNE.price
    }

    override fun getGiftFor(placedDate: LocalDate, orderItems: Map<Menu, Int>): Pair<Menu, Int> {
        require(isEligibleFor(placedDate, orderItems)) {
            "This order is not eligible for this event."
        }
        return Menu.CHAMPAGNE to 1
    }
}