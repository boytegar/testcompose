package apps.boytegar.dev.features.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import apps.boytegar.dev.shared.utils.Results
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel(
    private val splashDurationMillis: Long = 900,
    private val loginDelayMillis: Long = 250,
) : ViewModel() {
    private val _uiState = MutableStateFlow<Results<Unit>>(Results.loading())
    val uiState: StateFlow<Results<Unit>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(splashDurationMillis)
            _uiState.value = Results.empty()
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = Results.loading()
            delay(loginDelayMillis)
            _uiState.value = Results.success(Unit)
        }
    }
}
