package apps.boytegar.dev.features.home.data.local.datasource

import apps.boytegar.dev.features.home.data.local.entity.HomePhotoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import dev.zacsweers.metro.Inject

interface HomeLocalDataSource {
    suspend fun save(photos: List<HomePhotoEntity>)
    suspend fun get(): List<HomePhotoEntity>
    fun observe(): Flow<List<HomePhotoEntity>>
}

@Inject
class InMemoryHomeLocalDataSource : HomeLocalDataSource {
    private val photos = MutableStateFlow<List<HomePhotoEntity>>(emptyList())

    override suspend fun save(photos: List<HomePhotoEntity>) {
        this.photos.value = photos
    }

    override suspend fun get(): List<HomePhotoEntity> = photos.value

    override fun observe(): Flow<List<HomePhotoEntity>> = photos.asStateFlow()
}
