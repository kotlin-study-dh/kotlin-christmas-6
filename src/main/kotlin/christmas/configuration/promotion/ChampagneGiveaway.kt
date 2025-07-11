package christmas.configuration.promotion

import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Orders
import christmas.domain.product.Product
import christmas.repository.InMemoryProductRepository

class ChampagneGiveaway : Promotion {
    private val productRepository = InMemoryProductRepository

    companion object {
        private const val GIVEAWAY_PRODUCT_NAME = "샴페인"
        private val MIN_ORDER_PRICE_FOR_GIVEAWAY = Money.longValueOf(120_000, Currency.KRW)
    }

    override fun discountAmount(orders: Orders): Money {
        return Money.longValueOf(0, Currency.KRW)
    }

    override fun giveawayProduct(orders: Orders): Product? {
        return if (isSuitableForGiveaway(orders)) {
            productRepository.findByName(GIVEAWAY_PRODUCT_NAME)
        } else null
    }

    private fun isSuitableForGiveaway(orders: Orders): Boolean {
        return orders.totalPrice() >= MIN_ORDER_PRICE_FOR_GIVEAWAY
    }
}
