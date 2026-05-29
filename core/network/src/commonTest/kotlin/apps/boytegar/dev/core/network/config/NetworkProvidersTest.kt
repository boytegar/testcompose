package apps.boytegar.dev.core.network.config

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NetworkProvidersTest {
    @Test
    fun `provideNetworkConfig uses debug by default`() {
        val config = provideNetworkConfig("https://example.com/")

        assertEquals("https://example.com/", config.baseUrl)
        assertEquals(NetworkEnvironment.DEBUG, config.environment)
        assertTrue(config.enableLogging)
    }

    @Test
    fun `provideNetworkConfig can build release config`() {
        val config = provideNetworkConfig(
            baseUrl = "https://example.com/",
            environment = NetworkEnvironment.RELEASE,
        )

        assertEquals(NetworkEnvironment.RELEASE, config.environment)
        assertFalse(config.enableLogging)
    }
}
