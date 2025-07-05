package christmas.domain.event.badge

import christmas.domain.Price
import christmas.domain.order.Order

enum class DecemberEventBadge(val displayName: String, val minBenefitPrice: Price) {
    NONE("None", Price.from(0)),
    STAR("Star", Price.from(5_000)),
    TREE("Tree", Price.from(10_000)),
    SANTA("Santa", Price.from(20_000)),
    ;

    companion object {
        fun getBadgeFor(order: Order): DecemberEventBadge {
            val totalBenefitPrice = order.totalBenefitPrice
            return DecemberEventBadge.values().sortedByDescending{ it.minBenefitPrice }
                .find { it -> it.minBenefitPrice <= totalBenefitPrice } ?: NONE
        }
    }
}