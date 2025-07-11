package christmas.view

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
        val formattedNumber = DecimalFormat("#,###.##").format(price.amount)
        val currencyDisplayName = if (price.currency == Currency.KRW) "원" else price.currency
        println("${formattedNumber}$currencyDisplayName")
    }
}
