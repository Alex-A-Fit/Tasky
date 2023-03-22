package com.alexafit.onboardingAuthPresentation.login

import androidx.lifecycle.ViewModel
import com.alexafit.core.util.UiEvent
import com.alexafit.onboardingAuthPresentation.login.event.LoginEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.Login -> {
                // call api
            }
            LoginEvent.Register -> Unit
        }
    }
}
