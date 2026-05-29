package apps.boytegar.dev.features.auth.data.local.datasource

import apps.boytegar.dev.features.auth.data.local.entity.AuthSessionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface AuthLocalDataSource {
    suspend fun saveSession(session: AuthSessionEntity)
    suspend fun clearSession()
    suspend fun getSession(): AuthSessionEntity?
    fun observeSession(): Flow<AuthSessionEntity?>
}

class InMemoryAuthLocalDataSource : AuthLocalDataSource {
    private val session = MutableStateFlow<AuthSessionEntity?>(null)

    override suspend fun saveSession(session: AuthSessionEntity) {
        this.session.value = session
    }

    override suspend fun clearSession() {
        session.value = null
    }

    override suspend fun getSession(): AuthSessionEntity? = session.value

    override fun observeSession(): Flow<AuthSessionEntity?> = session.asStateFlow()
}
