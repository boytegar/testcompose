package apps.boytegar.dev.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import apps.boytegar.dev.core.common.result.AppResult
import apps.boytegar.dev.features.home.domain.model.HomePhoto
import apps.boytegar.dev.features.home.domain.usecase.GetHomePhotosUseCase
import apps.boytegar.dev.shared.utils.Results
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import dev.zacsweers.metro.Inject

@Inject
class HomeViewModel(
    private val getHomePhotosUseCase: GetHomePhotosUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<Results<List<HomePhoto>>>(Results.loading())
    val uiState: StateFlow<Results<List<HomePhoto>>> = _uiState.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = Results.loading()
            when (val result = getHomePhotosUseCase()) {
                is AppResult.Success -> {
                    val photos = result.value
                    _uiState.value = if (photos.isEmpty()) {
                        Results.empty<List<HomePhoto>>()
                    } else {
                        Results.success(photos)
                    }
                }

                is AppResult.Failure -> {
                    _uiState.value = Results.error(result.error.message)
                }
            }
        }
    }
}
