package apps.boytegar.dev.core.database.contract

import kotlinx.datetime.Instant

data class CacheRecord<T : Any>(
    val value: T,
    val cachedAt: Instant,
)
