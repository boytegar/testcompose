package apps.boytegar.dev.features.favorites.data.mapper

import apps.boytegar.dev.core.database.entity.FavoritePhotoEntity
import apps.boytegar.dev.features.favorites.domain.model.FavoritePhoto

fun FavoritePhotoEntity.toDomain(): FavoritePhoto =
    FavoritePhoto(
        albumId = albumId,
        id = id,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl,
    )

fun FavoritePhoto.toEntity(): FavoritePhotoEntity =
    FavoritePhotoEntity(
        albumId = albumId,
        id = id,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl,
    )
