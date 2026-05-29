package apps.boytegar.dev.core.testing

import apps.boytegar.dev.core.common.result.AppError
import apps.boytegar.dev.core.common.result.AppResult

object FixtureFactory {
    fun successString(value: String = "ok"): AppResult<String> = AppResult.Success(value)

    fun failureNetwork(
        message: String = "network error",
        code: Int = 500,
    ): AppResult<Nothing> = AppResult.Failure(AppError.Network(message = message, code = code))
}
