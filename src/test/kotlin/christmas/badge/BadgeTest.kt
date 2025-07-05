package christmas.badge

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BadgeTest {
    @Test
    fun `fails to find the badge when total discount amount is less than zero`() {
        // given
        val invalidTotalDiscountAmount = -1

        // when & then
        assertThatThrownBy { Badge.from(invalidTotalDiscountAmount) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Total discount amount must be greater than or equal to 0.")
    }

    @ParameterizedTest
    @CsvSource(
        "0, NONE",
        "4999, NONE",
        "5000, STAR",
        "5001, STAR",
        "9999, STAR",
        "10000, TREE",
        "10001, TREE",
        "19999, TREE",
        "20000, SANTA",
        "20001, SANTA",
    )
    fun `succeeds to find the badge based on total discount amount`(totalDiscountAmount: Int, expectedBadge: Badge) {
        // when
        val badge = Badge.from(totalDiscountAmount)

        // then
        assertThat(badge).isEqualTo(expectedBadge)
    }
}
