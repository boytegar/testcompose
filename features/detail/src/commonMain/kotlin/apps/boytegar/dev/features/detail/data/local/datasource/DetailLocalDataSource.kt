package apps.boytegar.dev.features.detail.data.local.datasource

import apps.boytegar.dev.features.detail.data.local.entity.DetailEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface DetailLocalDataSource {
    suspend fun saveAll(items: List<DetailEntity>)
    suspend fun add(item: DetailEntity)
    suspend fun getByWallet(walletId: String): List<DetailEntity>
    fun observe(): Flow<List<DetailEntity>>
}

class InMemoryDetailLocalDataSource : DetailLocalDataSource {
    private val items = MutableStateFlow<List<DetailEntity>>(emptyList())

    override suspend fun saveAll(items: List<DetailEntity>) {
        this.items.value = items
    }

    override suspend fun add(item: DetailEntity) {
        items.value = listOf(item) + items.value
    }

    override suspend fun getByWallet(walletId: String): List<DetailEntity> =
        items.value.filter { it.walletId == walletId }

    override fun observe(): Flow<List<DetailEntity>> = items.asStateFlow()
}
