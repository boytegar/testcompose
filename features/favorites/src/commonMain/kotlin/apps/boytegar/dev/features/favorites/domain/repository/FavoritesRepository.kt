package apps.boytegar.dev.features.favorites.domain.repository

import apps.boytegar.dev.core.common.result.AppResult
import apps.boytegar.dev.features.favorites.domain.model.FavoritePhoto
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun observeFavorites(): Flow<List<FavoritePhoto>>
    suspend fun addFavorite(photo: FavoritePhoto): AppResult<Unit>
    suspend fun removeFavorite(photoId: Int): AppResult<Unit>
}
