package apps.boytegar.dev.features.detail.domain.usecase

import androidx.paging.PagingData
import apps.boytegar.dev.features.detail.domain.model.DetailPhoto
import apps.boytegar.dev.features.detail.domain.repository.DetailRepository
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.Flow

@Inject
class GetDetailPhotosPagerUseCase(
    private val repository: DetailRepository,
) {
    operator fun invoke(): Flow<PagingData<DetailPhoto>> = repository.getPhotosPager()
}
