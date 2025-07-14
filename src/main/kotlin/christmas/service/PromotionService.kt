package christmas.service

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
import christmas.domain.product.Product
import christmas.domain.promotion.ChampagneGiveaway
import christmas.domain.promotion.ChristmasDdayDiscount
import christmas.domain.promotion.Promotion
import christmas.domain.promotion.StarDiscount
import christmas.domain.promotion.WeekdayDessertDiscount
import christmas.domain.promotion.WeekendMainDishDiscount

object PromotionService {
    private val promotions: List<Promotion> = listOf(
        ChampagneGiveaway,
        ChristmasDdayDiscount,
        StarDiscount,
        WeekdayDessertDiscount,
        WeekendMainDishDiscount,
    )

    fun findAppliedPromotions(orders: Orders): List<Promotion> {
        return promotions.filter { promotion -> isPromoted(promotion, orders) }
    }

    private fun isPromoted(promotion: Promotion, orders: Orders): Boolean {
        val discountAmount = promotion.discountAmount(orders)
        val giveawayProduct = promotion.giveawayProduct(orders)

        return !isNotPromoted(discountAmount, giveawayProduct)
    }

    private fun isNotPromoted(discountAmount: Money, giveawayProduct: Product?): Boolean {
        return discountAmount == Money.Companion.longValueOf(0, Currency.KRW)
                && giveawayProduct == null
    }
}
