package christmas.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalDate

class DateUtilTest {
    @ParameterizedTest
    @ValueSource(strings = ["2025-07-06", "2025-07-07", "2025-07-08", "2025-07-09", "2025-07-10"])
    fun `isWeekDay() returns true for SUN ~ THU`(date: String) {
        // given
        val localDate = LocalDate.parse(date)

        // when & then
        assertThat(localDate.isWeekDay()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(strings = ["2025-07-04", "2025-07-05"])
    fun `isWeekDay() returns false for FRI, SAT`(date: String) {
        // given
        val localDate = LocalDate.parse(date)

        // when & then
        assertThat(localDate.isWeekDay()).isFalse()
    }

    @ParameterizedTest
    @ValueSource(strings = ["2025-07-04", "2025-07-05"])
    fun `isWeekend() returns true for FRI, SAT`(date: String) {
        // given
        val localDate = LocalDate.parse(date)

        // when & then
        assertThat(localDate.isWeekend()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(strings = ["2025-07-06", "2025-07-07", "2025-07-08", "2025-07-09", "2025-07-10"])
    fun `isWeekend() returns false for SUN ~ THU`(date: String) {
        // given
        val localDate = LocalDate.parse(date)

        // when & then
        assertThat(localDate.isWeekend()).isFalse()
    }
}
