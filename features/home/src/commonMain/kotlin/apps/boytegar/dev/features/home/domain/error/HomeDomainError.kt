package apps.boytegar.dev.features.home.domain.error

sealed interface HomeDomainError {
    data object PhotosUnavailable : HomeDomainError
}
