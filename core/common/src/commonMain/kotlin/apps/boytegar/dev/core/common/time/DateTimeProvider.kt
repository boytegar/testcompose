package apps.boytegar.dev.core.common.time

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

interface DateTimeProvider {
    fun now(): Instant
    fun today(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDate
}

object SystemDateTimeProvider : DateTimeProvider {
    @OptIn(ExperimentalTime::class)
    override fun now(): Instant = Clock.System.now()

    override fun today(timeZone: TimeZone): LocalDate =
        LocalDate.fromEpochDays((now().epochSeconds / Constants.SECONDS_IN_DAY).toInt())

    private object Constants {
        const val SECONDS_IN_DAY = 86_400L
    }
}
