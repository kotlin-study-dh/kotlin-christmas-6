package christmas.policy

import christmas.domain.Order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ChristmasDDayDiscountTest {

    @ParameterizedTest
    @MethodSource("orders")
    fun `calculate christmas D-Day discount`(order: Order, discount: Int) {
        assertThat(ChristmasDDayDiscount.calculateDiscount(order)).isEqualTo(discount)
    }

    companion object {
        @JvmStatic
        fun orders(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Order(emptyMap(), 1), 1000),
                Arguments.of(Order(emptyMap(), 5), 1400),
                Arguments.of(Order(emptyMap(), 11), 2000),
                Arguments.of(Order(emptyMap(), 17), 2600),
                Arguments.of(Order(emptyMap(), 25), 3400),
                Arguments.of(Order(emptyMap(), 26), 0)
            )
        }
    }
}
