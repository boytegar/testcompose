package apps.boytegar.dev.core.network.config

import com.gyanoba.inspektor.Inspektor
import com.gyanoba.inspektor.LogLevel as InspektorLogLevel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class HttpClientFactory(
    private val config: NetworkConfig,
) {
    fun create(): HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    explicitNulls = false
                    isLenient = true
                },
            )
        }

        if (config.enableLogging) {
            val baseHost = Url(config.baseUrl).host

            install(Inspektor) {
                level = InspektorLogLevel.BODY
                filter { request ->
                    request.url.host.equals(baseHost, ignoreCase = true)
                }
                sanitizeHeader { header ->
                    header.equals(HttpHeaders.Authorization, ignoreCase = true) ||
                        header.equals(HttpHeaders.Cookie, ignoreCase = true)
                }
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        if (message.contains(HttpHeaders.Authorization, ignoreCase = true)) return
                        if (message.contains("token", ignoreCase = true)) return
                        config.logger.d("Network", message)
                    }
                }
                level = LogLevel.BODY
                sanitizeHeader { header ->
                    header.equals(HttpHeaders.Authorization, ignoreCase = true) ||
                        header.equals(HttpHeaders.Cookie, ignoreCase = true)
                }
            }
        } else {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.NONE
            }
        }

        defaultRequest {
            url(config.baseUrl)
            accept(ContentType.Application.Json)
            headers.append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }
    }
}
