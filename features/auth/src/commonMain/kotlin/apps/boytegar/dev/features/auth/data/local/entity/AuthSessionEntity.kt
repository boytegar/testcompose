package apps.boytegar.dev.features.auth.data.local.entity

data class AuthSessionEntity(
    val userId: String,
    val accessToken: String,
    val refreshToken: String?,
    val isAuthenticated: Boolean,
)
