package christmas.domain.event.util

import java.time.DayOfWeek
import java.time.DayOfWeek.FRIDAY
import java.time.DayOfWeek.SATURDAY

object DayChecker {

    fun DayOfWeek.isWeekend(): Boolean {
        return when (this) {
            FRIDAY -> true
            SATURDAY -> true
            else -> false
        }
    }

    fun DayOfWeek.isWeekday(): Boolean {
        return this.isWeekend().not()
    }
}
