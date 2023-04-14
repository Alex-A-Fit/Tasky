package com.alexafit.tasky

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexafit.core.local.datastore.PreferenceStorage
import com.alexafit.onboardingauthdomain.repository.OnboardingAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val onboardingAuthRepository: OnboardingAuthRepository,
    private val dataStore: PreferenceStorage
) : ViewModel() {
    private val _uiState = MutableStateFlow<MainActivityUiState>(MainActivityUiState.Loading)
    val uiState = _uiState.asStateFlow()

    var activeSession: MutableState<Boolean?> = mutableStateOf(null)
        private set

    var authenticationApiResponse: Result<Unit>? = null
        private set

    //Commenting out for now to reduce loading times for app.
    init {
//        runSplashScreen()
    }

    private suspend fun checkAuthentication() {
        viewModelScope.launch {
            if (authenticationApiResponse?.isSuccess == true) {
                activeSession.value = true
            } else {
                activeSession.value = false
                dataStore.clearAuthorizationKey()
            }
        }.join()
    }

    private suspend fun callAuthenticationApi() {
        viewModelScope.launch {
            authenticationApiResponse = onboardingAuthRepository.checkAuthentication()
        }.join()
    }

    private fun runSplashScreen() {
        viewModelScope.launch {
            callAuthenticationApi()
            checkAuthentication()
            delay(500L)
            if (activeSession.value != null) {
                _uiState.value = MainActivityUiState.Success
            } else {
                _uiState.value = MainActivityUiState.Loading
            }
        }
    }
}

sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    object Success : MainActivityUiState
}
