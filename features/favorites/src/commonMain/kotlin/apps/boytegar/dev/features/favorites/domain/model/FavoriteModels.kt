package apps.boytegar.dev.features.favorites.domain.model

data class FavoritePhoto(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
)
