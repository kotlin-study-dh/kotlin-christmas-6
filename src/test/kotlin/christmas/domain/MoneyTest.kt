package christmas.domain

import christmas.domain.menu.Money
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class MoneyTest {

    @Test
    fun `money can not be negative`() {
        assertThrows(IllegalArgumentException::class.java) {
            Money(-1)
        }
    }
}