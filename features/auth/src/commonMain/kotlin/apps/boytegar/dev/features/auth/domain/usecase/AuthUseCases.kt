package apps.boytegar.dev.features.auth.domain.usecase

import apps.boytegar.dev.core.common.result.AppResult
import apps.boytegar.dev.features.auth.domain.model.AuthSession
import apps.boytegar.dev.features.auth.domain.model.LoginRequest
import apps.boytegar.dev.features.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(request: LoginRequest): AppResult<AuthSession> = repository.login(request)
}

class LogoutUseCase(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(): AppResult<Unit> = repository.logout()
}

class GetSessionUseCase(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(): AppResult<AuthSession?> = repository.getSession()
}

class ObserveSessionUseCase(
    private val repository: AuthRepository,
) {
    operator fun invoke(): Flow<AuthSession?> = repository.observeSession()
}
