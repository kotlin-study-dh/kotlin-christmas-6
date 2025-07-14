package christmas.menu

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class MenuTest {
    @Test
    fun `fails to find the menu when korName does not match`() {
        val invalidKorName = "가지볶음"
        assertThatThrownBy { Menu.from(invalidKorName) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Cannot find menu for korName: $invalidKorName")
    }
}
