package christmas.domain.order

import christmas.domain.Menu
import christmas.domain.MenuSection
import christmas.domain.Price
import christmas.domain.sum
import java.time.LocalDate

class Order(
    val placedDate: LocalDate,
    val orderItems: Map<Menu, Int>
) {
    val totalPlacedPrice: Price
        get() = orderItems.map { (menu, amount) -> menu.price times amount }.sum()

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
    }

    companion object {
        private val MAX_TOTAL_MENU_QUANTITY = 20
    }
}
