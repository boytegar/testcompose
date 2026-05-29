package apps.boytegar.dev.features.detail.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DetailPhotoResponseDto(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
)
