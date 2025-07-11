package christmas.domain.promotion

import christmas.configuration.promotion.ChampagneGiveaway
import christmas.domain.money.Currency
import christmas.domain.money.Money
import christmas.domain.order.Order
import christmas.domain.order.Orders
import christmas.domain.product.Product
import christmas.domain.product.ProductType
import christmas.domain.product.ProductType.DRINK
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

class ChampagneGiveawayTest : PromotionTest {

    private val promotion = ChampagneGiveaway

    @Test
    fun `should giveaway when order price is 120_000`() {
        val product = Product(ProductType.MAIN, "steak", Money.longValueOf(120_000, Currency.KRW))
        val order = Order(product, 1)
        val orders = Orders(
            date = LocalDate.of(2023, 12, 1),
            orders = listOf(order)
        )

        val giveawayProduct = promotion.giveawayProduct(orders)

        assert(giveawayProduct == Product(DRINK, "샴페인", Money(BigDecimal(25000), Currency.KRW)))
    }

    @Test
    fun `should not giveaway when order price is 119_999`() {
        val product = Product(ProductType.MAIN, "steak", Money.longValueOf(119_999, Currency.KRW))
        val order = Order(product, 1)
        val orders = Orders(
            date = LocalDate.of(2023, 12, 1),
            orders = listOf(order)
        )

        val giveawayProduct = promotion.giveawayProduct(orders)

        assert(giveawayProduct == null)
    }
}
