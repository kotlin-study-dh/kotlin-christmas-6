package christmas.domain.event.discount

import christmas.domain.order.Menu
import christmas.domain.order.Price
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
    
    override fun checkSpecificEventConditions(placedDate: LocalDate, orderItems: Map<Menu, Int>): Boolean {
        return placedDate in specialDays
    }

    override fun getBenefitAmount(placedDate: LocalDate, orderItems: Map<Menu, Int>): Price {
        return Price.from(1_000)
    }
}