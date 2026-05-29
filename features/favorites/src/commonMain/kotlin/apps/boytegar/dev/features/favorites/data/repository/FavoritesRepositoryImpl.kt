package apps.boytegar.dev.features.favorites.data.repository

import apps.boytegar.dev.core.common.result.AppError
import apps.boytegar.dev.core.common.result.AppResult
import apps.boytegar.dev.features.favorites.data.local.dao.FavoritePhotoLocalDataSource
import apps.boytegar.dev.features.favorites.data.mapper.toDomain
import apps.boytegar.dev.features.favorites.data.mapper.toEntity
import apps.boytegar.dev.features.favorites.domain.model.FavoritePhoto
import apps.boytegar.dev.features.favorites.domain.repository.FavoritesRepository
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Inject
class FavoritesRepositoryImpl(
    private val favoritePhotoLocalDataSource: FavoritePhotoLocalDataSource,
) : FavoritesRepository {
    override fun observeFavorites(): Flow<List<FavoritePhoto>> =
        favoritePhotoLocalDataSource.observeFavorites().map { photos -> photos.map { it.toDomain() } }

    override suspend fun addFavorite(photo: FavoritePhoto): AppResult<Unit> =
        try {
            favoritePhotoLocalDataSource.upsert(photo.toEntity())
            AppResult.Success(Unit)
        } catch (throwable: Throwable) {
            AppResult.Failure(
                AppError.Persistence(
                    message = throwable.message ?: "Save favorite failed",
                    cause = throwable,
                ),
            )
        }

    override suspend fun removeFavorite(photoId: Int): AppResult<Unit> =
        try {
            favoritePhotoLocalDataSource.deleteById(photoId)
            AppResult.Success(Unit)
        } catch (throwable: Throwable) {
            AppResult.Failure(
                AppError.Persistence(
                    message = throwable.message ?: "Remove favorite failed",
                    cause = throwable,
                ),
            )
        }
}
