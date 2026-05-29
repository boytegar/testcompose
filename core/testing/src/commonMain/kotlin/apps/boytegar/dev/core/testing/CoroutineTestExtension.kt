package apps.boytegar.dev.core.testing

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineTestExtension(
    val dispatcher: TestDispatcher = StandardTestDispatcher(),
) {
    fun before() {
        Dispatchers.setMain(dispatcher)
    }

    fun after() {
        Dispatchers.resetMain()
    }
}
