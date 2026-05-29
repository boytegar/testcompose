package apps.boytegar.dev.features.detail.data.mapper

import apps.boytegar.dev.features.detail.data.remote.dto.DetailPhotoResponseDto
import apps.boytegar.dev.features.detail.domain.model.DetailPhoto

fun DetailPhotoResponseDto.toDomain(): DetailPhoto =
    DetailPhoto(
        albumId = albumId,
        id = id,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl,
    )
