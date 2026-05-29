package apps.boytegar.dev.features.favorites.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_photos")
data class FavoritePhotoEntity(
    val albumId: Int,
    @PrimaryKey val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
)
