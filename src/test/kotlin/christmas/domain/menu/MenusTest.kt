package christmas.domain.menu

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class MenusTest {

    @ParameterizedTest
    @EnumSource(Category::class)
    fun `find menu by category`(category: Category) {
        // given
        val menus = Menus(
            listOf(
                Menu("TAPAS", Money(5_000L), Category.APPETIZER),
                Menu("T-BORN", Money(55_000L), Category.MAIN),
                Menu("CHOCOLATE-CAKE", Money(15_000L), Category.DESSERT),
                Menu("DIET-COKE", Money(3_000L), Category.BEVERAGE)
            )
        )

        // when
        val actual = menus.findByCategory(category)

        // then
        assert(category === actual[0].category)
    }

    @Test
    fun `validate duplicate menu`() {
        // given
        val menuList = listOf(
            Menu("TAPAS", Money(5_000L), Category.APPETIZER),
            Menu("TAPAS", Money(55_000L), Category.MAIN),
        )

        // when & then
        assertThrows<IllegalArgumentException> { Menus(menuList) }
    }
}