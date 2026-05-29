package apps.boytegar.dev.features.home.domain.repository

import apps.boytegar.dev.core.common.result.AppResult
import apps.boytegar.dev.features.home.domain.model.HomePhoto
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getHomePhotos(): AppResult<List<HomePhoto>>
    fun observeHomePhotos(): Flow<List<HomePhoto>>
}
