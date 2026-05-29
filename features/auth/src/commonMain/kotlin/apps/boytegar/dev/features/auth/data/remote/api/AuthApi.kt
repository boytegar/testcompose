package apps.boytegar.dev.features.auth.data.remote.api

import apps.boytegar.dev.features.auth.data.remote.dto.AuthSessionResponseDto
import apps.boytegar.dev.features.auth.data.remote.dto.LoginRequestDto

interface AuthApi {
    suspend fun login(request: LoginRequestDto): AuthSessionResponseDto
    suspend fun logout(token: String)
}

class FakeAuthApi : AuthApi {
    override suspend fun login(request: LoginRequestDto): AuthSessionResponseDto {
        return AuthSessionResponseDto(
            userId = request.email.substringBefore('@').ifBlank { "user" },
            accessToken = "token-${request.email}",
            refreshToken = null,
        )
    }

    override suspend fun logout(token: String) = Unit
}
