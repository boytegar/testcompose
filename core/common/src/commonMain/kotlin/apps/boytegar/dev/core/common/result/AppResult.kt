package apps.boytegar.dev.core.common.result

sealed interface AppResult<out T> {
    data class Success<T>(val value: T) : AppResult<T>
    data class Failure(val error: AppError) : AppResult<Nothing>

    fun <R> map(transform: (T) -> R): AppResult<R> = when (this) {
        is Success -> Success(transform(value))
        is Failure -> this
    }

    fun <R> flatMap(transform: (T) -> AppResult<R>): AppResult<R> = when (this) {
        is Success -> transform(value)
        is Failure -> this
    }
}

inline fun <T> appResultOf(block: () -> T): AppResult<T> =
    try {
        AppResult.Success(block())
    } catch (throwable: Throwable) {
        AppResult.Failure(AppError.Unexpected(throwable.message ?: "Unexpected error", throwable))
    }
