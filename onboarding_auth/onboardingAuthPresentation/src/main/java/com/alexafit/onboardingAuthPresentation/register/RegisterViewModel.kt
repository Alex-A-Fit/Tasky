package com.alexafit.onboardingAuthPresentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexafit.core.util.UiText
import com.alexafit.onboardingAuthPresentation.R
import com.alexafit.onboardingAuthPresentation.register.event.RegisterEvent
import com.alexafit.onboardingAuthPresentation.register.event.RegisterUiEvent
import com.alexafit.onboardingauthdomain.useCase.OnboardingAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

    private val _uiEvent = Channel<RegisterUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: RegisterEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                RegisterEvent.Register -> {
                    if (registerState.validPassword && registerState.validEmailAddress) {
                        /**
                         * make api call to login and determine if login is successFul
                         */
                    } else {
                        /**
                         * Snackbar in place for error handling at the moment. Will discuss error handling in the future
                         */
                        when {
                            !registerState.validEmailAddress && !registerState.validPassword -> {
                                _uiEvent.send(
                                    RegisterUiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.text_error_invalid_email_and_password)
                                    )
                                )
                            }
                            !registerState.validEmailAddress -> {
                                _uiEvent.send(
                                    RegisterUiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.text_error_invalid_email)
                                    )
                                )
                            }
                            !registerState.validPassword -> {
                                _uiEvent.send(
                                    RegisterUiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.text_error_invalid_password)
                                    )
                                )
                            }
                            else -> {
                                _uiEvent.send(
                                    RegisterUiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.text_error_invalid_email_and_password)
                                    )
                                )
                            }
                        }
                    }
                }
                RegisterEvent.BackToLogin -> {
                    _uiEvent.send(
                        RegisterUiEvent.BackToLogin
                    )
                }
                is RegisterEvent.OnEmailAddressEnter -> {
                    val result = onboardingAuthUseCase.validateEmail(event.emailAddress)
                    registerState = registerState.copy(
                        emailAddress = result.userEmail,
                        validEmailAddress = result.validEmail
                    )
                }
                is RegisterEvent.OnEmailFocusChange -> {
                    registerState = registerState.copy(
                        isEmailHintVisible = !event.isFocused && registerState.emailAddress.isBlank()
                    )
                }
                is RegisterEvent.OnPasswordFocusChange -> {
                    registerState = registerState.copy(
                        isEmailHintVisible = !event.isFocused && registerState.password.isBlank()
                    )
                }
                is RegisterEvent.OnPasswordIconClicked -> {
                    registerState = registerState.copy(
                        isPasswordVisible = !event.isClicked
                    )
                }
                is RegisterEvent.OnPasswordEnter -> {
                    val result = onboardingAuthUseCase.validatePassword(event.password)
                    registerState = registerState.copy(
                        password = result.userPassword,
                        validPassword = result.validPassword
                    )
                }
                is RegisterEvent.OnNameEnter -> {
                    val result = onboardingAuthUseCase.validateName(event.name)
                    registerState = registerState.copy(
                        name = result.userName,
                        validName = result.validName
                    )
                }
                is RegisterEvent.OnNameFocusChange -> {
                    registerState = registerState.copy(
                        isNameHintVisible = !event.isFocused && registerState.name.isBlank()
                    )
                }
            }
        }
    }
}
