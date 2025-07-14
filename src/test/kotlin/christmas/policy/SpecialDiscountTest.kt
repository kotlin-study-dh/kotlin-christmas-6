package christmas.policy

import christmas.domain.Order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SpecialDiscountTest {

    @ParameterizedTest
    @MethodSource("orders")
    fun `calculate special discount`(order: Order, discount: Int) {
        assertThat(SpecialDiscount.calculateDiscount(order)).isEqualTo(discount)
    }

    companion object {
        @JvmStatic
        fun orders(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Order(emptyMap(), 1), 0),
                Arguments.of(Order(emptyMap(), 3), 1000),
                Arguments.of(Order(emptyMap(), 17), 1000),
                Arguments.of(Order(emptyMap(), 20), 0),
                Arguments.of(Order(emptyMap(), 25), 1000)
            )
        }
    }
}
