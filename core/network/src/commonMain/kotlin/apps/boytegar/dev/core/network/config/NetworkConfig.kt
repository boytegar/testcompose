package apps.boytegar.dev.core.network.config

import apps.boytegar.dev.core.common.logging.AppLogger
import apps.boytegar.dev.core.common.logging.NoOpLogger

data class NetworkConfig(
    val baseUrl: String,
    val environment: NetworkEnvironment,
    val enableLogging: Boolean = environment == NetworkEnvironment.DEBUG,
    val logger: AppLogger = NoOpLogger,
)
