package apps.boytegar.dev.shared.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

fun formatToHHmm(isoString: String): String {
    return try {
        val instant = Instant.parse(isoString)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        val hour = localDateTime.hour.toString().padStart(2, '0')
        val minute = localDateTime.minute.toString().padStart(2, '0')
        "$hour:$minute"
    } catch (e: Exception) {
        isoString // Return original string if parsing fails
    }
}

@OptIn(ExperimentalTime::class)
fun getYearToDateDays(): Int {
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    val firstDayOfYear = kotlinx.datetime.LocalDate(
        now.year,
        1,
        1
    )
    return (now.toEpochDays() - firstDayOfYear.toEpochDays()).toInt() + 1
}

