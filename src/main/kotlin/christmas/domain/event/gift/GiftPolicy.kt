package christmas.domain.event.gift

import christmas.domain.Menu
import christmas.domain.Price
import christmas.domain.event.EventPolicy
import java.time.LocalDate

abstract class GiftPolicy: EventPolicy() {
    abstract fun getGiftFor(placedDate: LocalDate, totalPrice: Price, orderItems: Map<Menu, Int>): Pair<Menu, Int>
}

object AllGiftPolicies {
    val values: List<GiftPolicy> = listOf(
        ChampagneGiftEventPolicy,
    )
} 