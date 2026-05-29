package apps.boytegar.dev.features.favorites.domain.usecase

import apps.boytegar.dev.core.common.result.AppResult
import apps.boytegar.dev.features.favorites.domain.model.FavoritePhoto
import apps.boytegar.dev.features.favorites.domain.repository.FavoritesRepository
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.Flow

@Inject
class ObserveFavoritesUseCase(
    private val repository: FavoritesRepository,
) {
    operator fun invoke(): Flow<List<FavoritePhoto>> = repository.observeFavorites()
}

@Inject
class AddFavoriteUseCase(
    private val repository: FavoritesRepository,
) {
    suspend operator fun invoke(photo: FavoritePhoto): AppResult<Unit> = repository.addFavorite(photo)
}

@Inject
class RemoveFavoriteUseCase(
    private val repository: FavoritesRepository,
) {
    suspend operator fun invoke(photoId: Int): AppResult<Unit> = repository.removeFavorite(photoId)
}
