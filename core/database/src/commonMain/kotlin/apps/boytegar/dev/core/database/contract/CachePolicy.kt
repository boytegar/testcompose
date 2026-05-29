package apps.boytegar.dev.core.database.contract

import kotlinx.datetime.Instant

data class CachePolicy(
    val ttlMillis: Long,
) {
    fun isExpired(cachedAt: Instant, nowEpochMillis: Long): Boolean {
        val ageMillis = nowEpochMillis - cachedAt.toEpochMilliseconds()
        return ageMillis > ttlMillis
    }
}
