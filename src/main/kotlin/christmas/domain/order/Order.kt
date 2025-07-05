package christmas.domain.order

import christmas.domain.Menu
import christmas.domain.MenuSection
import christmas.domain.Price
import christmas.domain.event.EventPolicy
import christmas.domain.event.AllEventPolicies
import christmas.domain.event.DiscountPolicy
import christmas.domain.event.GiftPolicy
import christmas.domain.event.badge.DecemberEventBadge
import christmas.domain.sum
import java.time.LocalDate

class Order(
    val placedDate: LocalDate,
    val orderItems: Map<Menu, Int>,
    val appliedEventPolicies: List<EventPolicy>,
) {
    val appliedDiscountPolicies: List<DiscountPolicy>
        get() = appliedEventPolicies.filterIsInstance<DiscountPolicy>()

    val appliedGiftPolicies: List<GiftPolicy>
        get() = appliedEventPolicies.filterIsInstance<GiftPolicy>()

    val totalPlacedPrice: Price
        get() = orderItems.map { (menu, amount) -> menu.price times amount }.sum()

    val totalDiscountedPrice: Price
        get() {
            val totalDiscountAmount = appliedDiscountPolicies.map { discountPolicy ->
                discountPolicy.getBenefitAmount(
                    OrderContext.from(this)
                )
            }.sum()
            return totalPlacedPrice minus totalDiscountAmount
        }

    val totalBenefitPrice: Price
        get() {
            val totalGiftPrice = appliedGiftPolicies.map { giftPolicy -> giftPolicy.getBenefitAmount(OrderContext.from(this)) }.sum()
            return totalDiscountedPrice plus totalGiftPrice
        }

    val eventBadge: DecemberEventBadge = DecemberEventBadge.getBadgeFor(this)

    init {
        require (orderItems.isNotEmpty()) {
            "Order items must not be empty"
        }
        require(!orderItems.values.contains(0)) {
            "Order must not contain items with zero quantity"
        }
        require(orderItems.filterNot { (orderItem, _) -> orderItem.section == MenuSection.BEVERAGE }.isNotEmpty()) {
            "At least one non-beverage item must be included in the order"
        }
        require(orderItems.values.sum() <= MAX_TOTAL_MENU_QUANTITY) {
            "You can order up to $MAX_TOTAL_MENU_QUANTITY menu items per order."
        }
        appliedEventPolicies.forEach { policy ->
            val eligibleFor = policy.isEligibleFor(OrderContext.from(this))
            if (!eligibleFor) throw IllegalArgumentException("Invalid policy for the order - policy: ${policy::class.simpleName}")
        }
    }

    companion object {
        private const val MAX_TOTAL_MENU_QUANTITY = 20

        fun of(placedDate: LocalDate, orderItems: Map<Menu, Int>): Order {
            val orderContext = OrderContext(placedDate, orderItems)
            val appliedPolicies = AllEventPolicies.values.filter { it.isEligibleFor(orderContext) }
            return Order(placedDate, orderItems, appliedPolicies)
        }
    }
}
