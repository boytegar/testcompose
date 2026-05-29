package apps.boytegar.dev.features.favorites.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import apps.boytegar.dev.core.common.result.AppResult
import apps.boytegar.dev.features.favorites.domain.model.FavoritePhoto
import apps.boytegar.dev.features.favorites.domain.usecase.AddFavoriteUseCase
import apps.boytegar.dev.features.favorites.domain.usecase.ObserveFavoritesUseCase
import apps.boytegar.dev.features.favorites.domain.usecase.RemoveFavoriteUseCase
import apps.boytegar.dev.shared.utils.Results
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Inject
class FavoritesViewModel(
    private val observeFavoritesUseCase: ObserveFavoritesUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<Results<List<FavoritePhoto>>>(Results.loading())
    val uiState: StateFlow<Results<List<FavoritePhoto>>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            observeFavoritesUseCase().collect { favorites ->
                _uiState.value = if (favorites.isEmpty()) {
                    Results.empty<List<FavoritePhoto>>()
                } else {
                    Results.success(favorites)
                }
            }
        }
    }

    fun toggleFavorite(photo: FavoritePhoto) {
        viewModelScope.launch {
            when (val state = uiState.value) {
                is Results.Success -> {
                    val alreadyFavorite = state.data.any { it.id == photo.id }
                    if (alreadyFavorite) {
                        removeFavoriteUseCase(photo.id)
                    } else {
                        addFavoriteUseCase(photo)
                    }
                }

                else -> {
                    addFavoriteUseCase(photo)
                }
            }
        }
    }
}
