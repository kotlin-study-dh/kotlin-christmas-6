package christmas.domain.promotion

import christmas.domain.money.Money
import christmas.domain.product.Product
import christmas.domain.promotion.strategy.PromotionStrategy

data class PromotionBenefit(
    val type: PromotionStrategy,
    val discountAmount: Money,
    val giveawayProduct: Product?,
) {
    fun benefitAmount(): Money {
        if (giveawayProduct != null) {
            return giveawayProduct.price
        }

        return discountAmount
    }
}
