package apps.boytegar.dev.features.home.data.repository

import apps.boytegar.dev.core.common.result.AppError
import apps.boytegar.dev.core.common.result.AppResult
import apps.boytegar.dev.features.home.data.local.datasource.HomeLocalDataSource
import apps.boytegar.dev.features.home.data.mapper.toDomain
import apps.boytegar.dev.features.home.data.mapper.toEntity
import apps.boytegar.dev.features.home.data.remote.datasource.HomeRemoteDataSource
import apps.boytegar.dev.features.home.domain.model.HomePhoto
import apps.boytegar.dev.features.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import dev.zacsweers.metro.Inject

@Inject
class HomeRepositoryImpl(
    private val remoteDataSource: HomeRemoteDataSource,
    private val localDataSource: HomeLocalDataSource,
) : HomeRepository {
    override suspend fun getHomePhotos(): AppResult<List<HomePhoto>> =
        try {
            val entities = remoteDataSource.getPhotos(page = 1, limit = 20).map { it.toEntity() }
            localDataSource.save(entities)
            AppResult.Success(entities.map { it.toDomain() })
        } catch (throwable: Throwable) {
            AppResult.Failure(
                AppError.Network(
                    message = throwable.message ?: "Load home photos failed",
                    cause = throwable,
                ),
            )
        }

    override fun observeHomePhotos(): Flow<List<HomePhoto>> = localDataSource.observe().map { photos ->
        photos.map { it.toDomain() }
    }
}
