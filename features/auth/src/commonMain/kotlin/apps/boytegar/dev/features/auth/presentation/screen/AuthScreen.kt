package apps.boytegar.dev.features.auth.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import apps.boytegar.dev.features.auth.di.AuthDi
import apps.boytegar.dev.shared.components.MultiStateView
import apps.boytegar.dev.shared.navigation.LocalAppNavigationActions
import apps.boytegar.dev.shared.utils.Results

@Composable
fun AuthScreen() {
    val viewModel = remember { AuthDi.authViewModel() }
    val navigationActions = LocalAppNavigationActions.current
    val uiState = viewModel.uiState.collectAsState().value
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(uiState) {
        if (uiState is Results.Success) {
            navigationActions.onAuthenticated()
        }
    }

    MultiStateView(
        modifier = Modifier.fillMaxSize(),
        state = viewModel.uiState,
        loadingLayout = { AuthSplashLayout() },
        emptyLayout = {
            AuthLoginLayout(
                username = username,
                password = password,
                onUsernameChange = { username = it },
                onPasswordChange = { password = it },
                onLoginClick = {
                    viewModel.login(username, password)
                },
            )
        },
    ) {
    }
}
