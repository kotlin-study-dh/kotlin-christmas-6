package christmas.menu

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.LocalDate

class OrderTest {
    private val defaultDate: LocalDate = LocalDate.of(2023, 12, 25)

    @Test
    fun `fails to create Order when Menus are not unique`() {
        // given
        val menuAndCount = listOf(
            "타파스" to 1,
            "티본 스테이크" to 2,
            "타파스" to 3 // Duplicate menu
        )

        // when & then
        assertThatThrownBy { Order.of(menuAndCount, defaultDate) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Menu must be unique.")
    }

    @Test
    fun `fails to create Order when only drinks are ordered`() {
        // given
        val menuAndCount = listOf(
            "제로 콜라" to 2,
            "레드 와인" to 1
        )

        // when & then
        assertThatThrownBy { Order.of(menuAndCount, defaultDate) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Cannot order drinks only.")
    }

    @Test
    fun `fails to create Order when each menu count is less than minimum`() {
        // given
        val menuAndCount = listOf(
            "타파스" to 0, // Invalid count
            "티본 스테이크" to 1
        )

        // when & then
        assertThatThrownBy { Order.of(menuAndCount, defaultDate) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Each menu count must be greater than or equal to 1.")
    }

    @Test
    fun `fails to create Order when total menu count exceeds maximum`() {
        // given
        val menuAndCount = listOf(
            "타파스" to 10,
            "티본 스테이크" to 11 // Total count exceeds 20
        )

        // when & then
        assertThatThrownBy { Order.of(menuAndCount, defaultDate) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Total menu count must not exceed 20.")
    }

    @Test
    fun `sums menu counts of a specific category`() {
        // given
        val menuAndCount = listOf(
            "초콜릿 케이크" to 2,
            "아이스크림" to 1,
            "제로 콜라" to 3
        )
        val order = Order.of(menuAndCount, defaultDate)

        // when
        val dessertCount = order.sumMenuCountsOf(Category.DESSERT)

        // then
        assertThat(dessertCount).isEqualTo(3)
    }
}
