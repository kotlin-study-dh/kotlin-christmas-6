package christmas.domain.order

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class MenuTest {

    @Test
    fun `find menu by display name`() {
        val displayName = "T-bone Steak"

        val actual = Menu.from(displayName)

        assertThat(actual).isEqualTo(Menu.T_BONE_STEAK)
    }

    @Test
    fun `throw exception when display name not found`() {
        val displayName = "Non-existent Menu"

        assertThatThrownBy { Menu.from(displayName) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `get menu price`() {
        val menu = Menu.T_BONE_STEAK

        val actual = menu.price

        assertThat(actual).isEqualTo(Price.from(55_000))
    }

    @Test
    fun `get menu section`() {
        val menu = Menu.CHOCOLATE_CAKE

        val actual = menu.section

        assertThat(actual).isEqualTo(MenuSection.DESSERT)
    }

    @Test
    fun `get menu display name`() {
        val menu = Menu.CHAMPAGNE

        val actual = menu.displayName

        assertThat(actual).isEqualTo("Champagne")
    }
} 