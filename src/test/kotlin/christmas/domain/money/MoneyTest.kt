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

    @Test
    fun `add money`() {
        val money1 = Money.doubleValueOf(3.33, Currency.EUR)
        val money2 = Money.doubleValueOf(6.67, Currency.EUR)
        val resultMoney = money1.add(money2)

        assert(resultMoney == Money.doubleValueOf(10.00, Currency.EUR))
    }
}
