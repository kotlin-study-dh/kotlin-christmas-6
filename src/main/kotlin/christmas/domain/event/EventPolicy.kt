package christmas.domain.event

import christmas.domain.order.Menu
import christmas.domain.order.Price
import christmas.domain.event.discount.AllDiscountPolicies
import christmas.domain.event.gift.AllGiftPolicies
import christmas.domain.order.totalPrice
import java.time.LocalDate

abstract class EventPolicy {
    abstract val name: String
    
    fun isEligibleFor(placedDate: LocalDate, orderItems: Map<Menu, Int>): Boolean {
        if (placedDate !in START_DATE..END_DATE) {
            return false
        }
        
        if (!isEligibleForMinimumPrice(orderItems.totalPrice())) {
            return false
        }
        
        return checkSpecificEventConditions(placedDate, orderItems)
    }
    
    abstract fun getBenefitAmount(placedDate: LocalDate, orderItems: Map<Menu, Int>): Price
    
    protected open fun isEligibleForMinimumPrice(totalPrice: Price): Boolean {
        return totalPrice.value >= MIN_PRICE_FOR_EVENT
    }
    
    protected abstract fun checkSpecificEventConditions(placedDate: LocalDate, orderItems: Map<Menu, Int>): Boolean
    
    companion object {
        private val START_DATE = LocalDate.of(2023, 12, 1)
        private val END_DATE = LocalDate.of(2023, 12, 31)
        private const val MIN_PRICE_FOR_EVENT = 10_000
    }
}

object AllEventPolicies {
    val values: List<EventPolicy> = AllDiscountPolicies.values + AllGiftPolicies.values
}