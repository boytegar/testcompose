package apps.boytegar.dev.core.database.contract

interface CacheStore<Key : Any, Value : Any> {
    suspend fun get(key: Key): Value?
    suspend fun put(key: Key, value: Value)
    suspend fun remove(key: Key)
    suspend fun clear()
}
