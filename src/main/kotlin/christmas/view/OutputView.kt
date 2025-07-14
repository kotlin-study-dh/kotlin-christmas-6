package christmas.view

import christmas.configuration.promotion.Promotion
import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
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

    fun printPromotionDetails(promotions: List<Promotion>, orders: Orders) {
        printAppliedPromotions(promotions, orders)
        printTotalBenefitPrice(promotions, orders)
    }

    private fun printAppliedPromotions(
        promotions: List<Promotion>,
        orders: Orders
    ) {
        println("<혜택 내역>")
        promotions.forEach { promotion ->
            val name = PromotionNameMapper.map(promotion)
            val amount = promotion.discountAmount(orders)
            println("$name: -${formatMoney(amount)}") // TODO display discount amount for giveaways
        }
        println()
    }

    private fun printTotalBenefitPrice(promotions: List<Promotion>, orders: Orders) {
        // TODO display discount amount for giveaways
        println("<총혜택 금액>")
        var benefitPrice = Money.longValueOf(0, Currency.KRW)
        promotions.forEach { benefitPrice = benefitPrice.add(it.discountAmount(orders)) }
        println("-${formatMoney(benefitPrice)}")
        println()
    }

    private fun formatMoney(money: Money): String {
        val amount = DecimalFormat("#,###.##").format(money.amount)
        val currency = if (money.currency == Currency.KRW) "원" else money.currency.toString()

        return amount + currency
    }
}
