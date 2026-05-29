package apps.boytegar.dev.features.auth.domain.error

sealed interface AuthDomainError {
    data object InvalidCredentials : AuthDomainError
    data object SessionUnavailable : AuthDomainError
}
