package christmas.database

import christmas.domain.money.Currency.KRW
import christmas.domain.money.Money
import christmas.domain.product.Product
import christmas.domain.product.ProductType.*
import java.math.BigDecimal

object ProductDatabase {
    val products = setOf(
        Product(APPETIZER, "양송이수프", Money(BigDecimal(6000), KRW)),
        Product(APPETIZER, "타파스", Money(BigDecimal(5500), KRW)),
        Product(APPETIZER, "시저샐러드", Money(BigDecimal(8000), KRW)),

        Product(MAIN, "티본스테이크", Money(BigDecimal(55000), KRW)),
        Product(MAIN, "바비큐립", Money(BigDecimal(54000), KRW)),
        Product(MAIN, "해산물파스타", Money(BigDecimal(35000), KRW)),
        Product(MAIN, "크리스마스파스타", Money(BigDecimal(25000), KRW)),

        Product(DESSERT, "초코케이크", Money(BigDecimal(15000), KRW)),
        Product(DESSERT, "아이스크림", Money(BigDecimal(5000), KRW)),

        Product(DRINK, "제로콜라", Money(BigDecimal(3000), KRW)),
        Product(DRINK, "레드와인", Money(BigDecimal(60000), KRW)),
        Product(DRINK, "샴페인", Money(BigDecimal(25000), KRW)),
    )
}
