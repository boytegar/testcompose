package apps.boytegar.dev.features.auth.data.repository

import apps.boytegar.dev.core.common.result.AppError
import apps.boytegar.dev.core.common.result.AppResult
import apps.boytegar.dev.features.auth.data.local.datasource.AuthLocalDataSource
import apps.boytegar.dev.features.auth.data.mapper.toDomain
import apps.boytegar.dev.features.auth.data.mapper.toDto
import apps.boytegar.dev.features.auth.data.mapper.toEntity
import apps.boytegar.dev.features.auth.data.remote.datasource.AuthRemoteDataSource
import apps.boytegar.dev.features.auth.domain.model.AuthSession
import apps.boytegar.dev.features.auth.domain.model.LoginRequest
import apps.boytegar.dev.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource,
    private val localDataSource: AuthLocalDataSource,
) : AuthRepository {
    override suspend fun login(request: LoginRequest): AppResult<AuthSession> =
        try {
            val response = remoteDataSource.login(request.toDto())
            val entity = response.toEntity()
            localDataSource.saveSession(entity)
            AppResult.Success(entity.toDomain())
        } catch (throwable: Throwable) {
            AppResult.Failure(AppError.Network(message = throwable.message ?: "Login failed", cause = throwable))
        }

    override suspend fun logout(): AppResult<Unit> =
        try {
            val session = localDataSource.getSession()
            if (session != null) {
                remoteDataSource.logout(session.accessToken)
            }
            localDataSource.clearSession()
            AppResult.Success(Unit)
        } catch (throwable: Throwable) {
            AppResult.Failure(AppError.Unexpected(message = throwable.message ?: "Logout failed", cause = throwable))
        }

    override suspend fun getSession(): AppResult<AuthSession?> =
        try {
            AppResult.Success(localDataSource.getSession()?.toDomain())
        } catch (throwable: Throwable) {
            AppResult.Failure(AppError.Persistence(message = throwable.message ?: "Read session failed", cause = throwable))
        }

    override fun observeSession(): Flow<AuthSession?> = localDataSource.observeSession().map { it?.toDomain() }
}
