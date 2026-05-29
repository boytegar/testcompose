package apps.boytegar.dev.navigation

import apps.boytegar.dev.features.home.domain.model.HomePhoto

class AppNavigator {
    fun startDestination(hasSession: Boolean): AppRoute =
        if (hasSession) AppRoute.Home else AppRoute.Auth

    fun onAuthenticated(): AppRoute = AppRoute.Home

    fun openFavorites(): AppRoute = AppRoute.Favorites

    fun openPhotoDetail(photo: HomePhoto): AppRoute =
        AppRoute.PhotoDetail(
            albumId = photo.albumId,
            id = photo.id,
            title = photo.title,
            url = photo.url,
            thumbnailUrl = photo.thumbnailUrl,
        )

    fun backToHome(): AppRoute = AppRoute.Home
}
