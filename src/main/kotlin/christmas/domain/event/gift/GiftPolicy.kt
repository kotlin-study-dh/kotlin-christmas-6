package christmas.domain.event.gift

import christmas.domain.order.Menu
import christmas.domain.event.EventPolicy
import java.time.LocalDate

abstract class GiftPolicy: EventPolicy() {
    abstract fun getGiftFor(placedDate: LocalDate, orderItems: Map<Menu, Int>): Pair<Menu, Int>
}

object AllGiftPolicies {
    val values: List<GiftPolicy> = listOf(
        ChampagneGiftEventPolicy,
    )
} 