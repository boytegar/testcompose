package apps.boytegar.dev.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun CoreTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = lightColorScheme(
        primary = CoreColorTokens.Primary,
        onPrimary = CoreColorTokens.OnPrimary,
        surface = CoreColorTokens.Surface,
        onSurface = CoreColorTokens.OnSurface,
        background = CoreColorTokens.SurfaceLight,
        onBackground = CoreColorTokens.OnSurfaceLight,
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = androidx.compose.material3.Typography(),
        content = content
    )
}
