package apps.boytegar.dev.core.database.contract

import kotlinx.coroutines.flow.Flow

interface PersistenceStore<Key : Any, Value : Any> {
    suspend fun findById(key: Key): Value?
    fun observeById(key: Key): Flow<Value?>
    suspend fun upsert(value: Value)
    suspend fun deleteById(key: Key)
}
