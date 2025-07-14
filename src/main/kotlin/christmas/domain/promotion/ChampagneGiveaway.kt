package christmas.domain.promotion

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
import christmas.domain.product.Product
import christmas.domain.product.ProductType.DRINK
import java.math.BigDecimal

object ChampagneGiveaway : Promotion {
    private const val GIVEAWAY_PRODUCT_NAME = "샴페인"
    private val MIN_ORDER_PRICE_FOR_GIVEAWAY = Money.longValueOf(120_000, Currency.KRW)

    override fun discountAmount(orders: Orders): Money {
        return Money.longValueOf(0, Currency.KRW)
    }

    override fun giveawayProduct(orders: Orders): Product? {
        return if (isSuitableForGiveaway(orders)) {
            Product(DRINK, GIVEAWAY_PRODUCT_NAME, Money(BigDecimal(25000), Currency.KRW))
        } else null
    }

    private fun isSuitableForGiveaway(orders: Orders): Boolean {
        return orders.totalPrice() >= MIN_ORDER_PRICE_FOR_GIVEAWAY
    }
}
