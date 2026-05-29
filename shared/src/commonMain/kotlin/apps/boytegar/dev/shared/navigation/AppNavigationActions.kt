package apps.boytegar.dev.shared.navigation

import androidx.compose.runtime.staticCompositionLocalOf

data class AppNavigationActions(
    val onAuthenticated: () -> Unit = {},
    val onHomePhotoClick: (albumId: Int, id: Int, title: String, url: String, thumbnailUrl: String) -> Unit = { _, _, _, _, _ -> },
    val onFavoritesClick: () -> Unit = {},
    val onFavoritePhotoClick: (albumId: Int, id: Int, title: String, url: String, thumbnailUrl: String) -> Unit = { _, _, _, _, _ -> },
    val onBack: () -> Unit = {},
)

val LocalAppNavigationActions = staticCompositionLocalOf { AppNavigationActions() }
