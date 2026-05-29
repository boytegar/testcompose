package apps.boytegar.dev.core.network.config

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.ktorfit
import io.ktor.client.HttpClient

class KtorfitFactory(
    private val config: NetworkConfig,
    private val httpClient: HttpClient,
) {
    fun create(): Ktorfit = ktorfit {
        baseUrl(config.baseUrl)
        httpClient(httpClient)
    }
}
