package apps.boytegar.dev.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import apps.boytegar.dev.features.auth.presentation.AuthScreen
import apps.boytegar.dev.features.detail.presentation.screen.DetailScreen
import apps.boytegar.dev.features.detail.domain.model.DetailPhoto
import apps.boytegar.dev.features.favorites.presentation.screen.FavoritesScreen
import apps.boytegar.dev.features.home.presentation.screen.HomeScreen
import apps.boytegar.dev.shared.navigation.AppNavigationActions
import apps.boytegar.dev.shared.navigation.LocalAppNavigationActions
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Composable
internal fun AppNavHost(
    startDestination: AppRoute = AppRoute.Auth,
    modifier: Modifier = Modifier,
) {
    val navConfiguration = remember {
        SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(AppRoute.Auth::class, AppRoute.Auth.serializer())
                    subclass(AppRoute.Home::class, AppRoute.Home.serializer())
                    subclass(AppRoute.Favorites::class, AppRoute.Favorites.serializer())
                    subclass(AppRoute.PhotoDetail::class, AppRoute.PhotoDetail.serializer())
                }
            }
        }
    }

    val backStack = rememberNavBackStack(navConfiguration, startDestination)

    CompositionLocalProvider(
        LocalAppNavigationActions provides AppNavigationActions(
            onAuthenticated = {
                backStack.removeLastOrNull()
                backStack.add(AppRoute.Home)
            },
            onHomePhotoClick = { albumId, id, title, url, thumbnailUrl ->
                backStack.add(
                    AppRoute.PhotoDetail(
                        albumId = albumId,
                        id = id,
                        title = title,
                        url = url,
                        thumbnailUrl = thumbnailUrl,
                    ),
                )
            },
            onFavoritesClick = {
                backStack.add(AppRoute.Favorites)
            },
            onFavoritePhotoClick = { albumId, id, title, url, thumbnailUrl ->
                backStack.add(
                    AppRoute.PhotoDetail(
                        albumId = albumId,
                        id = id,
                        title = title,
                        url = url,
                        thumbnailUrl = thumbnailUrl,
                    ),
                )
            },
            onBack = { backStack.removeLastOrNull() },
        ),
    ) {
        NavDisplay(
            backStack = backStack,
            modifier = modifier.fillMaxSize(),
            onBack = { backStack.removeLastOrNull() },
            transitionSpec = {
                slideInHorizontally(
                    animationSpec = tween(260),
                    initialOffsetX = { fullWidth -> fullWidth },
                ) togetherWith slideOutHorizontally(
                    animationSpec = tween(260),
                    targetOffsetX = { fullWidth -> -fullWidth / 3 },
                )
            },
            popTransitionSpec = {
                slideInHorizontally(
                    animationSpec = tween(260),
                    initialOffsetX = { fullWidth -> -fullWidth / 3 },
                ) togetherWith slideOutHorizontally(
                    animationSpec = tween(260),
                    targetOffsetX = { fullWidth -> fullWidth },
                )
            },
            predictivePopTransitionSpec = {
                slideInHorizontally(
                    animationSpec = tween(260),
                    initialOffsetX = { fullWidth -> -fullWidth / 3 },
                ) togetherWith slideOutHorizontally(
                    animationSpec = tween(260),
                    targetOffsetX = { fullWidth -> fullWidth },
                )
            },
            entryProvider = entryProvider {
                entry<AppRoute.Auth> {
                    AuthScreen()
                }

                entry<AppRoute.Home> {
                    HomeScreen()
                }

                entry<AppRoute.Favorites> {
                    FavoritesScreen()
                }

                entry<AppRoute.PhotoDetail> { route ->
                    DetailScreen(
                        selectedPhoto = DetailPhoto(
                            albumId = route.albumId,
                            id = route.id,
                            title = route.title,
                            url = route.url,
                            thumbnailUrl = route.thumbnailUrl,
                        ),
                        onBack = { backStack.removeLastOrNull() },
                    )
                }
            },
        )
    }
}
