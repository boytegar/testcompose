package apps.boytegar.dev.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoute : NavKey {
    @Serializable
    data object Auth : AppRoute

    @Serializable
    data object Home : AppRoute

    @Serializable
    data object Favorites : AppRoute

    @Serializable
    data class PhotoDetail(
        val albumId: Int,
        val id: Int,
        val title: String,
        val url: String,
        val thumbnailUrl: String,
    ) : AppRoute
}
