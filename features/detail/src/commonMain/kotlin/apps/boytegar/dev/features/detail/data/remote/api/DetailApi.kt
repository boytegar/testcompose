package apps.boytegar.dev.features.detail.data.remote.api

import apps.boytegar.dev.features.detail.data.remote.dto.DetailPhotoResponseDto
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

interface DetailApi {
    @GET("photos")
    suspend fun getPhotos(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int,
    ): List<DetailPhotoResponseDto>
}

class FakeDetailApi : DetailApi {
    private val photos = List(1500) { index ->
        val id = index + 1
        val colorSeed = (id * 53) % 255
        val colorHex = colorSeed.toString(16).padStart(2, '0')
        DetailPhotoResponseDto(
            albumId = (id / 50) + 1,
            id = id,
            title = "Photo $id",
            url = "https://via.placeholder.com/600/${colorHex}c952",
            thumbnailUrl = "https://via.placeholder.com/150/${colorHex}c952",
        )
    }

    override suspend fun getPhotos(
        page: Int,
        limit: Int,
    ): List<DetailPhotoResponseDto> {
        val fromIndex = ((page - 1).coerceAtLeast(0)) * limit
        if (fromIndex >= photos.size) return emptyList()
        val toIndex = (fromIndex + limit).coerceAtMost(photos.size)
        return photos.subList(fromIndex, toIndex)
    }
}
