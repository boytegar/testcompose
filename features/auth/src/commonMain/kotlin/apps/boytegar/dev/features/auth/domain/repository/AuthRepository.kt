package apps.boytegar.dev.features.auth.domain.repository

import apps.boytegar.dev.core.common.result.AppResult
import apps.boytegar.dev.features.auth.domain.model.AuthSession
import apps.boytegar.dev.features.auth.domain.model.LoginRequest
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(request: LoginRequest): AppResult<AuthSession>
    suspend fun logout(): AppResult<Unit>
    suspend fun getSession(): AppResult<AuthSession?>
    fun observeSession(): Flow<AuthSession?>
}
