package apps.boytegar.dev.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun CoreTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = CoreColorTokens.PrimaryDark,
            onPrimary = CoreColorTokens.OnPrimaryDark,
            surface = CoreColorTokens.SurfaceDark,
            onSurface = CoreColorTokens.OnSurfaceDark,
            background = CoreColorTokens.BackgroundDark,
            onBackground = CoreColorTokens.OnBackgroundDark,
        )
    } else {
        lightColorScheme(
            primary = CoreColorTokens.Primary,
            onPrimary = CoreColorTokens.OnPrimary,
            surface = CoreColorTokens.Surface,
            onSurface = CoreColorTokens.OnSurface,
            background = CoreColorTokens.SurfaceLight,
            onBackground = CoreColorTokens.OnSurfaceLight,
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
