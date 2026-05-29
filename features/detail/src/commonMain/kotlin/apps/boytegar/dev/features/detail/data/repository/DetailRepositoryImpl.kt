package apps.boytegar.dev.features.detail.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import apps.boytegar.dev.features.detail.data.remote.datasource.DetailRemoteDataSource
import apps.boytegar.dev.features.detail.data.remote.datasource.DetailPhotoPagingSource
import apps.boytegar.dev.features.detail.domain.model.DetailPhoto
import apps.boytegar.dev.features.detail.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import dev.zacsweers.metro.Inject

@Inject
class DetailRepositoryImpl(
    private val remoteDataSource: DetailRemoteDataSource,
) : DetailRepository {
    override fun getPhotosPager(): Flow<PagingData<DetailPhoto>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 40,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                DetailPhotoPagingSource(remoteDataSource)
            },
        ).flow
}
