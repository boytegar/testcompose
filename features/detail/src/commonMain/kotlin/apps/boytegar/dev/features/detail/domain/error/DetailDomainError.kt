package apps.boytegar.dev.features.detail.domain.error

sealed interface DetailDomainError {
    data object PhotosUnavailable : DetailDomainError
}
