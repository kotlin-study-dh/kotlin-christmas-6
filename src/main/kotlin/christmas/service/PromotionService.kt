package christmas.service

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
import christmas.domain.product.Product
import christmas.domain.promotion.PromotionBenefit
import christmas.domain.promotion.eventbadge.EventBadge
import christmas.domain.promotion.strategy.*

object PromotionService {
    private val promotionStrategies: List<PromotionStrategy> = listOf(
        ChampagneGiveaway,
        ChristmasDdayDiscount,
        StarDiscount,
        WeekdayDessertDiscount,
        WeekendMainDishDiscount,
    )

    fun findPromotionBenefits(orders: Orders): List<PromotionBenefit> {
        val applicableStrategies = promotionStrategies
            .filter { promotion -> promotion.meetsMinimumOrderPrice(orders) }
            .filter { promotion -> isPromoted(promotion, orders) }

        return applicableStrategies.map { strategy ->
            PromotionBenefit(
                strategy,
                strategy.discountAmount(orders),
                strategy.giveawayProduct(orders)
            )
        }
    }

    private fun isPromoted(promotionStrategy: PromotionStrategy, orders: Orders): Boolean {
        val discountAmount = promotionStrategy.discountAmount(orders)
        val giveawayProduct = promotionStrategy.giveawayProduct(orders)

        return !isNotPromoted(discountAmount, giveawayProduct)
    }

    private fun isNotPromoted(discountAmount: Money, giveawayProduct: Product?): Boolean {
        return discountAmount == Money.Companion.longValueOf(0, Currency.KRW)
                && giveawayProduct == null
    }

    fun findGiveawayProduct(promotionBenefits: List<PromotionBenefit>): Product? {
        return promotionBenefits.find { it.type == ChampagneGiveaway }
            ?.giveawayProduct
    }

    fun findBenefitPrice(promotionBenefits: List<PromotionBenefit>): Money {
        var benefitPrice = Money.longValueOf(0, Currency.KRW)

        promotionBenefits.forEach {
            benefitPrice = benefitPrice.add(it.discountAmount)
            benefitPrice = benefitPrice.add(it.giveawayProduct?.price ?: Money.longValueOf(0, Currency.KRW))
        }

        return benefitPrice
    }

    fun findDiscountPrice(promotionBenefits: List<PromotionBenefit>): Money {
        var discountPrice = Money.longValueOf(0, Currency.KRW)

        promotionBenefits.forEach {
            discountPrice = discountPrice.add(it.discountAmount)
        }

        return discountPrice
    }

    fun findEventBadge(promotionBenefits: List<PromotionBenefit>): EventBadge? {
        val benefitPrice = findBenefitPrice(promotionBenefits)
        return EventBadge.of(benefitPrice)
    }
}
