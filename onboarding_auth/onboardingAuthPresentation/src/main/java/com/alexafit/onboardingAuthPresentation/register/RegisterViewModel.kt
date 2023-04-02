package com.alexafit.onboardingAuthPresentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexafit.core.util.UiEvent
import com.alexafit.core.util.UiText
import com.alexafit.onboardingAuthPresentation.R
import com.alexafit.onboardingAuthPresentation.event.user.RegisterUserEvent
import com.alexafit.onboardingauthdomain.useCase.OnboardingAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val onboardingAuthUseCase: OnboardingAuthUseCase
) : ViewModel() {

    var registerState by mutableStateOf(RegisterState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: RegisterUserEvent) {
        viewModelScope.launch {
            when (event) {
                RegisterUserEvent.NavigateToAgenda -> {
                    if (registerState.validPassword && registerState.validEmailAddress && registerState.validName) {
                        /**
                         * make api call to login and determine if login is successFul
                         */
                    } else {
                        /**
                         * Snackbar in place for error handling at the moment. Will discuss error handling in the future
                         */
                        when {
                            !registerState.validEmailAddress && !registerState.validPassword && !registerState.validName -> {
                                _uiEvent.send(
                                    UiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.text_error_invalid_email_password_and_name)
                                    )
                                )
                            }
                            !registerState.validEmailAddress && !registerState.validPassword -> {
                                _uiEvent.send(
                                    UiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.text_error_invalid_email_and_password)
                                    )
                                )
                            }
                            !registerState.validEmailAddress -> {
                                _uiEvent.send(
                                    UiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.text_error_invalid_email)
                                    )
                                )
                            }
                            !registerState.validPassword -> {
                                _uiEvent.send(
                                    UiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.text_error_invalid_password)
                                    )
                                )
                            }
                            !registerState.validName -> {
                                _uiEvent.send(
                                    UiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.text_error_invalid_name)
                                    )
                                )
                            }
                            else -> {
                                _uiEvent.send(
                                    UiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.text_error_invalid_email_password_and_name)
                                    )
                                )
                            }
                        }
                    }
                }
                RegisterUserEvent.NavigateToLogin -> {
                    _uiEvent.send(
                        UiEvent.Navigate
                    )
                }
                is RegisterUserEvent.OnEmailAddressEnter -> {
                    val result = onboardingAuthUseCase.validateEmail(event.emailAddress)
                    registerState = registerState.copy(
                        emailAddress = result.userEmail,
                        validEmailAddress = result.validEmail
                    )
                }
                is RegisterUserEvent.OnEmailFocusChange -> {
                    registerState = registerState.copy(
                        isEmailHintVisible = !event.isFocused && registerState.emailAddress.isBlank()
                    )
                }
                is RegisterUserEvent.OnPasswordFocusChange -> {
                    registerState = registerState.copy(
                        isEmailHintVisible = !event.isFocused && registerState.password.isBlank()
                    )
                }
                is RegisterUserEvent.OnPasswordIconClicked -> {
                    registerState = registerState.copy(
                        isPasswordVisible = !event.isClicked
                    )
                }
                is RegisterUserEvent.OnPasswordEnter -> {
                    val result = onboardingAuthUseCase.validatePassword(event.password)
                    registerState = registerState.copy(
                        password = result.userPassword,
                        validPassword = result.validPassword
                    )
                }
                is RegisterUserEvent.OnNameEnter -> {
                    val result = onboardingAuthUseCase.validateName(event.name)
                    registerState = registerState.copy(
                        name = result.userName,
                        validName = result.validName
                    )
                }
                is RegisterUserEvent.OnNameFocusChange -> {
                    registerState = registerState.copy(
                        isNameHintVisible = !event.isFocused && registerState.name.isBlank()
                    )
                }
            }
        }
    }
}
