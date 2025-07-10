package christmas.repository

import christmas.domain.money.Currency.KRW
import christmas.domain.money.Money
import christmas.domain.product.Product
import christmas.domain.product.ProductType.APPETIZER
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class InMemoryProductRepositoryTest {
    @Test
    fun `should find correct Product by name`() {
        val findByName = InMemoryProductRepository.findByName("양송이수프")

        assert(
            findByName == Product(
                APPETIZER,
                "양송이수프",
                Money(BigDecimal(6000), KRW),
            )
        )
    }

    @Test
    fun `should return null when product does not exist`() {
        val findByName = InMemoryProductRepository.findByName("aaaaaaaaaaaaaaa")
        assert(findByName == null)
    }
}
