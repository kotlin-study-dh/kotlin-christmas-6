package christmas.domain.product

import christmas.domain.money.Currency
import christmas.domain.money.Money
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ProductTest {
    @Test
    fun currency() {
        val product = Product(ProductType.MAIN, "steak", Money(BigDecimal(20000), Currency.KRW))
        assert(product.currency() == Currency.KRW)
    }
}
