package apps.boytegar.dev.features.auth.data.mapper

import apps.boytegar.dev.features.auth.data.local.entity.AuthSessionEntity
import apps.boytegar.dev.features.auth.data.remote.dto.AuthSessionResponseDto
import apps.boytegar.dev.features.auth.data.remote.dto.LoginRequestDto
import apps.boytegar.dev.features.auth.domain.model.AuthSession
import apps.boytegar.dev.features.auth.domain.model.LoginRequest

fun LoginRequest.toDto(): LoginRequestDto =
    LoginRequestDto(email = email, password = password)

fun AuthSessionResponseDto.toEntity(): AuthSessionEntity =
    AuthSessionEntity(
        userId = userId,
        accessToken = accessToken,
        refreshToken = refreshToken,
        isAuthenticated = true,
    )

fun AuthSessionEntity.toDomain(): AuthSession =
    AuthSession(
        userId = userId,
        accessToken = accessToken,
        refreshToken = refreshToken,
        isAuthenticated = isAuthenticated,
    )
