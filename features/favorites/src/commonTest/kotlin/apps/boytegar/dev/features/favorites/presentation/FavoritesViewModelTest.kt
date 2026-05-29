package apps.boytegar.dev.features.favorites.presentation

import apps.boytegar.dev.core.common.result.AppResult
import apps.boytegar.dev.core.testing.CoroutineTestExtension
import apps.boytegar.dev.features.favorites.domain.model.FavoritePhoto
import apps.boytegar.dev.features.favorites.domain.repository.FavoritesRepository
import apps.boytegar.dev.features.favorites.domain.usecase.AddFavoriteUseCase
import apps.boytegar.dev.features.favorites.domain.usecase.ObserveFavoritesUseCase
import apps.boytegar.dev.features.favorites.domain.usecase.RemoveFavoriteUseCase
import apps.boytegar.dev.features.favorites.presentation.viewmodel.FavoritesViewModel
import apps.boytegar.dev.shared.utils.Results
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class FavoritesViewModelTest {
    private val coroutineTestExtension = CoroutineTestExtension()

    @BeforeTest
    fun before() {
        coroutineTestExtension.before()
    }

    @AfterTest
    fun after() {
        coroutineTestExtension.after()
    }

    @Test
    fun `starts empty when there are no favorites`() = runTest {
        val repository = FakeFavoritesRepository()
        val viewModel = buildViewModel(repository)

        advanceUntilIdle()

        assertTrue(viewModel.uiState.value is Results.Empty)
    }

    @Test
    fun `toggle favorite adds then removes the item`() = runTest {
        val repository = FakeFavoritesRepository()
        val viewModel = buildViewModel(repository)
        val photo = favoritePhoto()

        advanceUntilIdle()
        viewModel.toggleFavorite(photo)
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value is Results.Success)
        assertEquals(listOf(photo), (viewModel.uiState.value as Results.Success).data)

        viewModel.toggleFavorite(photo)
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value is Results.Empty)
    }

    private fun buildViewModel(repository: FavoritesRepository): FavoritesViewModel =
        FavoritesViewModel(
            observeFavoritesUseCase = ObserveFavoritesUseCase(repository),
            addFavoriteUseCase = AddFavoriteUseCase(repository),
            removeFavoriteUseCase = RemoveFavoriteUseCase(repository),
        )

    private fun favoritePhoto() = FavoritePhoto(
        albumId = 1,
        id = 42,
        title = "Saved favorite",
        url = "https://example.com/photos/42",
        thumbnailUrl = "https://example.com/photos/thumb/42",
    )

    private class FakeFavoritesRepository : FavoritesRepository {
        private val favorites = MutableStateFlow<List<FavoritePhoto>>(emptyList())

        override fun observeFavorites(): Flow<List<FavoritePhoto>> = favorites.asStateFlow()

        override suspend fun addFavorite(photo: FavoritePhoto): AppResult<Unit> {
            favorites.value = favorites.value.filterNot { it.id == photo.id } + photo
            return AppResult.Success(Unit)
        }

        override suspend fun removeFavorite(photoId: Int): AppResult<Unit> {
            favorites.value = favorites.value.filterNot { it.id == photoId }
            return AppResult.Success(Unit)
        }
    }
}
