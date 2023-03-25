package com.alexafit.onboardingAuthPresentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexafit.core.util.UiEvent
import com.alexafit.core.util.UiText
import com.alexafit.onboardingAuthPresentation.R
import com.alexafit.onboardingAuthPresentation.login.event.LoginEvent
import com.alexafit.onboardingauthdomain.useCase.OnboardingAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val onboardingAuthUseCase: OnboardingAuthUseCase
) : ViewModel() {

    var emailAddress by mutableStateOf("")
        private set

    var validEmailAddress by mutableStateOf(false)
        private set

    var password by mutableStateOf("")
        private set

    var validPassword by mutableStateOf(false)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                LoginEvent.Login -> {
                    if (validPassword && validEmailAddress) {
                        /**
                         * make api call to login and determine if login is successFul
                         */
                    } else {
                        kotlin.run {
                            /**
                             * Snackbar in place for error handling at the moment. Will discuss error handling in the future
                             */
                            when {
                                !validEmailAddress && !validPassword -> {
                                    _uiEvent.send(
                                        UiEvent.ShowSnackbar(
                                            UiText.StringResource(R.string.text_error_invalid_email_and_password)
                                        )
                                    )
                                }
                                !validEmailAddress -> {
                                    _uiEvent.send(
                                        UiEvent.ShowSnackbar(
                                            UiText.StringResource(R.string.text_error_invalid_email)
                                        )
                                    )
                                }
                                !validPassword -> {
                                    _uiEvent.send(
                                        UiEvent.ShowSnackbar(
                                            UiText.StringResource(R.string.text_error_invalid_password)
                                        )
                                    )
                                }
                                else -> {
                                    _uiEvent.send(
                                        UiEvent.ShowSnackbar(
                                            UiText.StringResource(R.string.text_error_invalid_email_and_password)
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                LoginEvent.Register -> Unit
            }
        }
    }

    fun onEmailAddressEnter(email: String) {
        val result = onboardingAuthUseCase.validateEmail(email)
        this.emailAddress = result.first
        this.validEmailAddress = result.second
    }

    fun onPasswordEnter(password: String) {
        val result = onboardingAuthUseCase.validatePassword(password)
        this.password = result.first
        this.validPassword = result.second
    }
}
