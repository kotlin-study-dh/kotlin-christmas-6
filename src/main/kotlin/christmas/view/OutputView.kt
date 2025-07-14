package christmas.view

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
import christmas.domain.product.Product
import christmas.domain.promotion.PromotionBenefit
import christmas.domain.promotion.eventbadge.EventBadge
import java.text.DecimalFormat

object OutputView {
    fun printEventDescription(month: Int, day: Int) {
        println("${month}월 ${day}일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
        println()
    }

    fun printOrderDetails(orders: Orders) {
        println("<주문 메뉴>")
        orders.orders.forEach {
            println("${it.product.name} ${it.amount}개")
        }
        println()
    }

    fun printTotalPriceBeforeDiscount(price: Money) {
        println("<할인 전 총주문 금액>")
        println(formatMoney(price))
        println()
    }

    fun printGiveawayProduct(product: Product?) {
        println("<증정 메뉴>")

        if (product == null) {
            println("없음")
        } else {
            println("${product.name} 1개")
        }

        println()
    }

    fun printPromotionBenefits(promotionBenefits: List<PromotionBenefit>) {
        println("<혜택 내역>")

        if (promotionBenefits.isEmpty()) {
            println("없음")
            println()
            return
        }

        promotionBenefits.forEach { benefit ->
            val name = PromotionNameMapper.map(benefit.type)
            val amount = benefit.benefitAmount()
            println("$name: -${formatMoney(amount)}")
        }
        println()
    }

    fun printBenefitPrice(benefitPrice: Money) {
        println("<총혜택 금액>")

        if (benefitPrice.isZero().not()) print("-")

        println(formatMoney(benefitPrice))
        println()
    }

    fun printPaymentPrice(paymentPrice: Money) {
        println("<할인 후 예상 결제 금액>")
        println(formatMoney(paymentPrice))
        println()
    }

    fun printEventBadge(eventBadge: EventBadge?) {
        println("<12월 이벤트 배지>")
        println(EventBadgeNameMapper.map(eventBadge))
    }

    private fun formatMoney(money: Money): String {
        val amount = DecimalFormat("#,###.##").format(money.amount)
        val currency = if (money.currency == Currency.KRW) "원" else money.currency.toString()

        return amount + currency
    }
}
