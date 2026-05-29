package apps.boytegar.dev

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import apps.boytegar.dev.navigation.AppNavHost
import apps.boytegar.dev.navigation.AppRoute
import apps.boytegar.dev.theme.AppTheme

@Composable
fun App(
    onThemeChanged: @Composable (isDark: Boolean) -> Unit = {},
    startDestination: AppRoute = AppRoute.Auth,
    content: (@Composable () -> Unit)? = null,
) = AppTheme(onThemeChanged = onThemeChanged) {
    content?.invoke() ?: AppNavHost(startDestination = startDestination)
}

@Preview
@Composable
private fun AppPreview() {
    App(
        onThemeChanged = {},
        startDestination = AppRoute.Home,
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text("Home preview")
            }
        },
    )
}
