package christmas.util

import java.time.DayOfWeek
import java.time.LocalDate

fun LocalDate.isWeekDay(): Boolean {
    val dayOfWeek = this.dayOfWeek
    return dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY
}
