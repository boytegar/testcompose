package apps.boytegar.dev.features.detail.domain.repository

import androidx.paging.PagingData
import apps.boytegar.dev.features.detail.domain.model.DetailPhoto
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getPhotosPager(): Flow<PagingData<DetailPhoto>>
}
