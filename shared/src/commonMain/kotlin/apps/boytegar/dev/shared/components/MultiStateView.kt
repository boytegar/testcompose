package apps.boytegar.dev.shared.components


import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import apps.boytegar.dev.shared.utils.Results
import kotlinx.coroutines.flow.StateFlow


@Composable
fun <T : Any> MultiStateView(
    modifier: Modifier = Modifier,
    state: StateFlow<Results<T>>,
    emptyLayout: @Composable () -> Unit = { EmptyLayout() },
    loadingLayout: @Composable () -> Unit = { LoadingLayout() },
    errorLayout: @Composable (String?) -> Unit = { message -> ErrorLayout(message) },
    connectionLayout: @Composable ((messages: String?) -> Unit)? = null,
    content: @Composable (data: T) -> Unit,
) {
    Crossfade(
        targetState = state.collectAsState().value,
        modifier = modifier, label = ""
    ) {
        when (val states = it) {
            is Results.Success -> content(states.data)
            is Results.Loading -> loadingLayout()
            is Results.Error -> errorLayout(states.message)
            is Results.Connection -> connectionLayout?.invoke(states.message) ?: DefaultConnectionLayout(states.message)
            is Results.Empty -> emptyLayout()
            else -> {}
        }
    }
}

@Composable
private fun DefaultConnectionLayout(message: String?) {
    ErrorLayout(message = message ?: "Koneksi bermasalah")
}