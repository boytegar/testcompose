package apps.boytegar.dev.core.network.config

import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient

fun provideNetworkConfig(
    baseUrl: String,
    environment: NetworkEnvironment = NetworkEnvironment.DEBUG,
): NetworkConfig = NetworkConfig(
    baseUrl = baseUrl,
    environment = environment,
)

fun provideHttpClient(config: NetworkConfig): HttpClient =
    HttpClientFactory(config).create()

fun provideKtorfit(
    config: NetworkConfig,
    httpClient: HttpClient,
): Ktorfit = KtorfitFactory(config, httpClient).create()
