package apps.boytegar.dev.core.common.result

sealed interface AppError {
    val message: String

    data class Network(
        override val message: String,
        val code: Int? = null,
        val cause: Throwable? = null,
    ) : AppError

    data class Validation(
        override val message: String,
    ) : AppError

    data class Persistence(
        override val message: String,
        val cause: Throwable? = null,
    ) : AppError

    data class Unexpected(
        override val message: String,
        val cause: Throwable? = null,
    ) : AppError
}
