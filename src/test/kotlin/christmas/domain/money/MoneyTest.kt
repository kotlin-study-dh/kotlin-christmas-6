package christmas.domain.money

import org.junit.jupiter.api.Test
import java.math.BigDecimal

class MoneyTest {
    @Test
    fun `multiply Money - 3,33 multiply 3 equals 9,99`() {
        val money = Money(BigDecimal.valueOf(3.33), Currency.EUR).multiply(3)
        assert(money == Money(BigDecimal.valueOf(9.99), Currency.EUR))
    }

    @Test
    fun `multiply Money - 0,00 multiply 3 equals 0,00`() {
        val money = Money(BigDecimal.valueOf(0.00), Currency.EUR).multiply(3)
        assert(money == Money(BigDecimal.valueOf(0.00), Currency.EUR))
    }
}
