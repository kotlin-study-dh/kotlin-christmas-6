package christmas.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PriceTest {
    @Test
    fun `can be created from an integer value`() {
        // Given
        val value: Int = 1_000_000

        // When
        val price = Price.from(value)

        // Then
        assertThat(price).isEqualTo(Price(1_000_000.toDouble()))
    }

    @Test
    fun `multiply value`() {
        // Given
        val price = Price.from(1_111)

        // When
        val actual = price times 3

        // Then
        assertThat(actual).isEqualTo(Price.from(3_333))
    }

    @Test
    fun `add values in iterable`() {
        // Given
        val prices = listOf(Price.from(1_500), Price.from(27_000), Price.from(5_600))

        // When
        val actual = prices.sum()

        // Then
        assertThat(actual).isEqualTo(Price.from(34_100))
    }
}