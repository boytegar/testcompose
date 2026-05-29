package apps.boytegar.dev.features.auth.data.remote.dto

data class LoginRequestDto(
    val email: String,
    val password: String,
)

data class AuthSessionResponseDto(
    val userId: String,
    val accessToken: String,
    val refreshToken: String?,
)
