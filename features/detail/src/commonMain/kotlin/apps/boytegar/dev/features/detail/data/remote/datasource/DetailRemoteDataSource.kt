package apps.boytegar.dev.features.detail.data.remote.datasource

import apps.boytegar.dev.features.detail.data.remote.api.DetailApi
import apps.boytegar.dev.features.detail.data.remote.dto.DetailPhotoResponseDto
import dev.zacsweers.metro.Inject

interface DetailRemoteDataSource {
    suspend fun getPhotos(page: Int, limit: Int): List<DetailPhotoResponseDto>
}

@Inject
class DetailRemoteDataSourceImpl(
    private val api: DetailApi,
) : DetailRemoteDataSource {
    override suspend fun getPhotos(page: Int, limit: Int): List<DetailPhotoResponseDto> =
        api.getPhotos(page, limit)
}
