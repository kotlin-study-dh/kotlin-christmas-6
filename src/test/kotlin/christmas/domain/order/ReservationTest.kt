package christmas.domain.order

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class ReservationTest {

    @Test
    fun `only valid period is DEC 2023`() {
        assertThrows<IllegalArgumentException> { Reservation(LocalDate.of(2023, 11, 30), Order(listOf())) }
        assertThrows<IllegalArgumentException> { Reservation(LocalDate.of(2024, 1, 1), Order(listOf())) }
    }
}