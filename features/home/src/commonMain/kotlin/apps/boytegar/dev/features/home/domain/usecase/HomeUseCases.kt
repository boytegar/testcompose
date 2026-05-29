package apps.boytegar.dev.features.home.domain.usecase

import apps.boytegar.dev.core.common.result.AppResult
import apps.boytegar.dev.features.home.domain.model.HomePhoto
import apps.boytegar.dev.features.home.domain.repository.HomeRepository
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.Flow

@Inject
class GetHomePhotosUseCase(
    private val repository: HomeRepository,
) {
    suspend operator fun invoke(): AppResult<List<HomePhoto>> = repository.getHomePhotos()
}

@Inject
class ObserveHomePhotosUseCase(
    private val repository: HomeRepository,
) {
    operator fun invoke(): Flow<List<HomePhoto>> = repository.observeHomePhotos()
}
