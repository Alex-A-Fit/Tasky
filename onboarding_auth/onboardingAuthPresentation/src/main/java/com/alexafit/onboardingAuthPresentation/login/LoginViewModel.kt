package com.alexafit.onboardingAuthPresentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexafit.core.util.UiText
import com.alexafit.onboardingAuthPresentation.R
import com.alexafit.onboardingAuthPresentation.login.event.LoginEvent
import com.alexafit.onboardingAuthPresentation.login.event.LoginUiEvent
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

    var loginState by mutableStateOf(LoginState())
        private set

    private val _uiEvent = Channel<LoginUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                LoginEvent.Login -> {
                    if (loginState.validPassword && loginState.validEmailAddress) {
                        /**
                         * make api call to login and determine if login is successFul
                         */
                    } else {
                        /**
                         * Snackbar in place for error handling at the moment. Will discuss error handling in the future
                         */
                        when {
                            !loginState.validEmailAddress && !loginState.validPassword -> {
                                _uiEvent.send(
                                    LoginUiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.text_error_invalid_email_and_password)
                                    )
                                )
                            }
                            !loginState.validEmailAddress -> {
                                _uiEvent.send(
                                    LoginUiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.text_error_invalid_email)
                                    )
                                )
                            }
                            !loginState.validPassword -> {
                                _uiEvent.send(
                                    LoginUiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.text_error_invalid_password)
                                    )
                                )
                            }
                            else -> {
                                _uiEvent.send(
                                    LoginUiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.text_error_invalid_email_and_password)
                                    )
                                )
                            }
                        }
                    }
                }
                LoginEvent.Register -> {
                    _uiEvent.send(
                        LoginUiEvent.Register
                    )
                }
                is LoginEvent.OnEmailAddressEnter -> {
                    val result = onboardingAuthUseCase.validateEmail(event.emailAddress)
                    loginState = loginState.copy(
                        emailAddress = result.emailResult,
                        validEmailAddress = result.validEmail
                    )
                }
                is LoginEvent.OnEmailFocusChange -> {
                    loginState = loginState.copy(
                        isEmailHintVisible = !event.isFocused && loginState.emailAddress.isBlank()
                    )
                }

                is LoginEvent.OnPasswordFocusChange -> {
                    loginState = loginState.copy(
                        isEmailHintVisible = !event.isFocused && loginState.password.isBlank()
                    )
                }
                is LoginEvent.OnPasswordIconClicked -> {
                    loginState = loginState.copy(
                        isPasswordVisible = !event.isClicked
                    )
                }
                is LoginEvent.OnPasswordEnter -> {
                    val result = onboardingAuthUseCase.validatePassword(event.password)
                    loginState = loginState.copy(
                        password = result.passwordResult,
                        validPassword = result.validPassword
                    )
                }
            }
        }
    }
}
