package christmas.view

import christmas.domain.order.Order
import christmas.domain.order.OrderContext
import christmas.domain.sum

object OutputView {
    fun printBenefit(order: Order) {
        println("Preview of the event benefits you’ll receive at Woowa Restaurant on December ${order.placedDate.dayOfMonth}!")
        println()

        printMenuItems(order)
        printTotalPlacedPrice(order)
        printGift(order)
        printEventBenefits(order)
        printTotalBenefitPrice(order)
        printExpectedPayment(order)
        printEventBadge(order)
    }

    private fun printMenuItems(order: Order) {
        println("<Ordered Menu>")
        order.orderItems.forEach { item, quantity ->
            println("${item.displayName} - $quantity")
        }
        println()
    }

    private fun printTotalPlacedPrice(order: Order) {
        println("<Total price before discount>")
        println("${order.totalPlacedPrice} WON")
        println()
    }

    private fun printGift(order: Order) {
        println("<Gift Menu>")
        if (order.appliedGiftPolicies.isEmpty()) {
            println("No gift")
        }
        order.appliedGiftPolicies.forEach { giftPolicy ->
            val (gift, quantity) = giftPolicy.getGiftFor(OrderContext.from(order))
            println("$quantity ${gift.displayName}")
        }
        println()
    }

    private fun printEventBenefits(order: Order) {
        println("<Event Benefits>")
        order.appliedEventPolicies.forEach { eventPolicy ->
            val benefitAmount = eventPolicy.getBenefitAmount(OrderContext.from(order))
            println("${eventPolicy.name}: -$benefitAmount WON")
        }
        println()
    }

    private fun printTotalBenefitPrice(order: Order) {
        println("<Total Benefit Amount>")
        val totalBenefit = order.appliedEventPolicies
            .map { event -> event.getBenefitAmount(OrderContext.from(order)) }
            .sum()
        println("-$totalBenefit WON")
        println()
    }

    private fun printExpectedPayment(order: Order) {
        println("<Expected Payment After Discounts>")
        println("${order.totalDiscountedPrice} WON")
        println()
    }

    private fun printEventBadge(order: Order) {
        println("<December Event Badge>")
        println(order.eventBadge.displayName)
        println()
    }
}