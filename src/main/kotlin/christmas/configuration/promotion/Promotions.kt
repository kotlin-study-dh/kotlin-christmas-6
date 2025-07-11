package christmas.configuration.promotion

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
import christmas.domain.product.Product

data class Promotions(
    private val promotions: List<Promotion> = listOf(
        ChampagneGiveaway,
        ChristmasDdayDiscount,
        StarDiscount,
        WeekdayDessertDiscount,
        WeekendMainDishDiscount,
    )
) {
    fun findAppliedPromotions(orders: Orders): List<Promotion> {
        return promotions.filter { promotion -> isPromoted(promotion, orders) }
    }

    private fun isPromoted(promotion: Promotion, orders: Orders): Boolean {
        val discountAmount = promotion.discountAmount(orders)
        val giveawayProduct = promotion.giveawayProduct(orders)

        return !isNotPromoted(discountAmount, giveawayProduct)
    }

    private fun isNotPromoted(discountAmount: Money, giveawayProduct: Product?): Boolean {
        return discountAmount == Money.longValueOf(0, Currency.KRW)
                && giveawayProduct == null
    }
}
