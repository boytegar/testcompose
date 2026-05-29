package apps.boytegar.dev

import apps.boytegar.dev.features.home.domain.model.HomePhoto
import apps.boytegar.dev.navigation.AppNavigator
import apps.boytegar.dev.navigation.AppRoute
import kotlin.test.Test
import kotlin.test.assertEquals

class AppNavigatorTest {
    private val navigator = AppNavigator()

    @Test
    fun `should start at auth when no session`() {
        assertEquals(AppRoute.Auth, navigator.startDestination(hasSession = false))
    }

    @Test
    fun `should start at home when session exists`() {
        assertEquals(AppRoute.Home, navigator.startDestination(hasSession = true))
    }

    @Test
    fun `should move auth to home and open photo detail`() {
        assertEquals(AppRoute.Home, navigator.onAuthenticated())
        assertEquals(AppRoute.Favorites, navigator.openFavorites())
        assertEquals(
            AppRoute.PhotoDetail(
                albumId = 1,
                id = 3,
                title = "ea molestias quasi exercitationem repellat qui ipsa sit aut",
                url = "https://via.placeholder.com/600/92c952",
                thumbnailUrl = "https://via.placeholder.com/150/92c952",
            ),
            navigator.openPhotoDetail(
                HomePhoto(
                    albumId = 1,
                    id = 3,
                    title = "ea molestias quasi exercitationem repellat qui ipsa sit aut",
                    url = "https://via.placeholder.com/600/92c952",
                    thumbnailUrl = "https://via.placeholder.com/150/92c952",
                ),
            ),
        )
        assertEquals(AppRoute.Home, navigator.backToHome())
    }
}
