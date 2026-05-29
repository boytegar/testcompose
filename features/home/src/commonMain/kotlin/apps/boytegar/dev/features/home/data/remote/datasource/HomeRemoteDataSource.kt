package apps.boytegar.dev.features.home.data.remote.datasource

import apps.boytegar.dev.features.home.data.remote.api.HomeApi
import apps.boytegar.dev.features.home.data.remote.dto.HomePhotoResponseDto
import dev.zacsweers.metro.Inject

interface HomeRemoteDataSource {
    suspend fun getPhotos(page: Int, limit: Int): List<HomePhotoResponseDto>
}

@Inject
class HomeRemoteDataSourceImpl(
    private val api: HomeApi,
) : HomeRemoteDataSource {
    override suspend fun getPhotos(page: Int, limit: Int): List<HomePhotoResponseDto> =
        api.getPhotos(page, limit)
}
