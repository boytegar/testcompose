package apps.boytegar.dev.features.home.data.mapper

import apps.boytegar.dev.features.home.data.local.entity.HomePhotoEntity
import apps.boytegar.dev.features.home.data.remote.dto.HomePhotoResponseDto
import apps.boytegar.dev.features.home.domain.model.HomePhoto

fun HomePhotoResponseDto.toEntity(): HomePhotoEntity =
    HomePhotoEntity(
        albumId = albumId,
        id = id,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl,
    )

fun HomePhotoEntity.toDomain(): HomePhoto =
    HomePhoto(
        albumId = albumId,
        id = id,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl,
    )
