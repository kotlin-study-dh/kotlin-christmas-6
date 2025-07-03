package christmas.domain.order

import christmas.domain.Menu
import christmas.domain.Price
import christmas.domain.sum
import java.time.LocalDate

class Order(
    val placedDate: LocalDate,
    val orderItems: Map<Menu, Int>
) {
    fun calculatePrice(): Price {
        return orderItems.map { (menu, amount) -> menu.price times amount }.sum()
    }

}

