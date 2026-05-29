package apps.boytegar.dev.features.favorites.data.local.dao

import apps.boytegar.dev.core.database.entity.FavoritePhotoEntity
import kotlinx.coroutines.flow.Flow

interface FavoritePhotoLocalDataSource {
    fun observeFavorites(): Flow<List<FavoritePhotoEntity>>
    suspend fun upsert(photo: FavoritePhotoEntity)
    suspend fun deleteById(photoId: Int)
}
