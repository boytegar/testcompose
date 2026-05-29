package apps.boytegar.dev.features.auth.presentation

import apps.boytegar.dev.core.testing.CoroutineTestExtension
import apps.boytegar.dev.shared.utils.Results
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModelTest {
    private val coroutineTestExtension = CoroutineTestExtension()

    @BeforeTest
    fun before() {
        coroutineTestExtension.before()
    }

    @AfterTest
    fun after() {
        coroutineTestExtension.after()
    }

    @Test
    fun `splash ends at login page`() = runTest {
        val viewModel = AuthViewModel(
            splashDurationMillis = 0,
            loginDelayMillis = 0,
        )

        advanceUntilIdle()

        assertTrue(viewModel.uiState.value is Results.Empty)
    }

    @Test
    fun `login bypasses to success`() = runTest {
        val viewModel = AuthViewModel(
            splashDurationMillis = 0,
            loginDelayMillis = 0,
        )

        advanceUntilIdle()
        viewModel.login("user", "password")
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value is Results.Success)
    }
}
