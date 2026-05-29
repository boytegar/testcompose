package apps.boytegar.dev.features.auth.domain.model

data class AuthSession(
    val userId: String,
    val accessToken: String,
    val refreshToken: String?,
    val isAuthenticated: Boolean,
)

data class LoginRequest(
    val email: String,
    val password: String,
)
