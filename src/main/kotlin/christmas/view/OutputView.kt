package christmas.view

import christmas.domain.order.Order

object OutputView {
    fun printBenefit(order: Order) {
        println("Preview of the event benefits you’ll receive at Woowa Restaurant on December ${order.placedDate.dayOfMonth}!")
        println()
        println("<Ordered Menu>")
        order.orderItems.forEach {
            item, quantity -> println("${item.displayName} - $quantity")
        }

        println("<Total price before discount>")
        println("${order.totalPlacedPrice} WON")

        println("<Gift Menu>")
        TODO("Gift")

        println("<Event Benefits>")
        order.appliedDiscountPolicies.forEach {

        }

        println("<Total Benefit Amount>")
        TODO()
        println("<Expected Payment After Discounts>")
        TODO()
        println("<December Event Badge>")
    }
}