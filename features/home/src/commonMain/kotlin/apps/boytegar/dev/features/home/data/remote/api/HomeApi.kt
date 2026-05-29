package apps.boytegar.dev.features.home.data.remote.api

import apps.boytegar.dev.features.home.data.remote.dto.HomePhotoResponseDto
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

interface HomeApi {
    @GET("photos")
    suspend fun getPhotos(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int,
    ): List<HomePhotoResponseDto>
}

class FakeHomeApi : HomeApi {
    private val photos = List(1500) { index ->
        val id = index + 1
        val colorSeed = (id * 37) % 255
        val colorHex = colorSeed.toString(16).padStart(2, '0')
        HomePhotoResponseDto(
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
    ): List<HomePhotoResponseDto> {
        val fromIndex = ((page - 1).coerceAtLeast(0)) * limit
        if (fromIndex >= photos.size) return emptyList()
        val toIndex = (fromIndex + limit).coerceAtMost(photos.size)
        return photos.subList(fromIndex, toIndex)
    }
}
