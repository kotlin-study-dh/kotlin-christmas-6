package christmas

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import christmas.domain.order.Menu
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled
class ApplicationTest : NsTest() {
    @Test
    fun `print all titles`() {
        assertSimpleTest {
            run(
                "3",
                "${Menu.T_BONE_STEAK.displayName}-1,${Menu.BBQ_RIBS.displayName}-1,${Menu.CHOCOLATE_CAKE.displayName}-2,${Menu.COKE_ZERO.displayName}}-1"
            )
            assertThat(output()).contains(
                "<Ordered Menu>",
                "<Total price before discount>",
                "<Gift Menu>",
                "<Event Benefits>",
                "<Total Benefit Amount>",
                "<Expected Payment After Discounts>",
                "<December Event Badge>"
            )
        }
    }

    @Test
    fun `print no event benefits`() {
        assertSimpleTest {
            run("26", "Tapas-1,Coke Zero-1")
            assertThat(output()).contains("<Event Benefits>$LINE_SEPARATOR".toString() + "None")
        }
    }

    override fun runMain() {
        main()
    }

    companion object {
        private val LINE_SEPARATOR = System.lineSeparator()
    }
}
