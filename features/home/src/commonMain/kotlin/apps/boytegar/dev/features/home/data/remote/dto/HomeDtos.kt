package apps.boytegar.dev.features.home.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class HomePhotoResponseDto(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
)
