package apps.boytegar.dev.features.auth.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import apps.boytegar.dev.features.auth.di.AuthDi
import apps.boytegar.dev.shared.components.MultiStateView
import apps.boytegar.dev.shared.navigation.LocalAppNavigationActions
import apps.boytegar.dev.shared.utils.Results

@Composable
fun AuthScreen() {
    val viewModel = rememberAuthViewModel()
    val navigationActions = LocalAppNavigationActions.current
    val uiState = viewModel.uiState.collectAsState().value

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
                onPhoneLoginClick = { viewModel.loginWithPhone("081234567890") },
                onGoogleLoginClick = viewModel::loginWithGoogle,
                onFacebookLoginClick = viewModel::loginWithFacebook,
                onXLoginClick = viewModel::loginWithX,
            )
        },
    ) {
    }
}

@Composable
private fun rememberAuthViewModel() = androidx.compose.runtime.remember { AuthDi.authViewModel() }
