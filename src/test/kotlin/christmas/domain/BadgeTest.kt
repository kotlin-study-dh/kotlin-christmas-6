package christmas.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BadgeTest {

    @ParameterizedTest()
    @ValueSource(ints = [0, 1, 4999])
    fun `choose NONE badge`(totalBenefit: Int) {
        val badge = Badge.choose(totalBenefit)
        Assertions.assertThat(badge).isEqualTo(Badge.NONE)
    }

    @ParameterizedTest()
    @ValueSource(ints = [5000, 5001, 9999])
    fun `choose STAR badge`(totalBenefit: Int) {
        val badge = Badge.choose(totalBenefit)
        Assertions.assertThat(badge).isEqualTo(Badge.STAR)
    }

    @ParameterizedTest()
    @ValueSource(ints = [10000, 10001, 19999])
    fun `choose TREE badge`(totalBenefit: Int) {
        val badge = Badge.choose(totalBenefit)
        Assertions.assertThat(badge).isEqualTo(Badge.TREE)
    }

    @ParameterizedTest()
    @ValueSource(ints = [20000, 20001, 100000])
    fun `choose SANTA badge`(totalBenefit: Int) {
        val badge = Badge.choose(totalBenefit)
        Assertions.assertThat(badge).isEqualTo(Badge.SANTA)
    }
}
