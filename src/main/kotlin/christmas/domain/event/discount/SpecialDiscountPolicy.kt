package christmas.domain.event.discount

import christmas.domain.Price
import christmas.domain.order.OrderContext
import java.time.LocalDate

object SpecialDiscountPolicy: DiscountPolicy() {
    override val name = "Special Discount"
    private val specialDays = setOf(
        LocalDate.of(2023, 12, 3),
        LocalDate.of(2023, 12, 10),
        LocalDate.of(2023, 12, 17),
        LocalDate.of(2023, 12, 24),
        LocalDate.of(2023, 12, 25),
        LocalDate.of(2023, 12, 31),
    )
    
    override fun checkSpecificEventConditions(orderContext: OrderContext): Boolean {
        return orderContext.placedDate in specialDays
    }

    override fun getBenefitAmount(orderContext: OrderContext): Price {
        return Price.from(1_000)
    }
}