package apps.boytegar.dev.features.detail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import apps.boytegar.dev.features.detail.domain.model.DetailPhoto
import apps.boytegar.dev.features.detail.domain.usecase.GetDetailPhotosPagerUseCase
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.Flow

@Inject
class DetailViewModel(
    getDetailPhotosPagerUseCase: GetDetailPhotosPagerUseCase,
) : ViewModel() {
    val photos: Flow<PagingData<DetailPhoto>> =
        getDetailPhotosPagerUseCase().cachedIn(viewModelScope)
}
