package apps.boytegar.dev.features.auth.data.remote.datasource

import apps.boytegar.dev.features.auth.data.remote.api.AuthApi
import apps.boytegar.dev.features.auth.data.remote.dto.AuthSessionResponseDto
import apps.boytegar.dev.features.auth.data.remote.dto.LoginRequestDto

interface AuthRemoteDataSource {
    suspend fun login(request: LoginRequestDto): AuthSessionResponseDto
    suspend fun logout(token: String)
}

class AuthRemoteDataSourceImpl(
    private val api: AuthApi,
) : AuthRemoteDataSource {
    override suspend fun login(request: LoginRequestDto): AuthSessionResponseDto = api.login(request)

    override suspend fun logout(token: String) = api.logout(token)
}
