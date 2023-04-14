package com.alexafit.onboardingAuthPresentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexafit.core.util.UiText
import com.alexafit.onboardingAuthPresentation.R
import com.alexafit.onboardingAuthPresentation.event.uievent.LoginUiEvent
import com.alexafit.onboardingAuthPresentation.event.user.LoginUserEvent
import com.alexafit.onboardingauthdomain.model.remote.LoginUser
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

    fun onEvent(event: LoginUserEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                LoginUserEvent.NavigateToAgenda -> {
                    if (loginState.validPassword && loginState.validEmailAddress) {
                        loginUser()
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
                LoginUserEvent.NavigateToRegister -> {
                    _uiEvent.send(
                        LoginUiEvent.NavigateToRegisterScreen
                    )
                }
                is LoginUserEvent.OnEmailAddressEnter -> {
                    val result = onboardingAuthUseCase.validateEmail(event.emailAddress)
                    loginState = loginState.copy(
                        emailAddress = result.userEmail,
                        validEmailAddress = result.validEmail
                    )
                }
                is LoginUserEvent.OnEmailFocusChange -> {
                    loginState = loginState.copy(
                        isEmailHintVisible = !event.isFocused && loginState.emailAddress.isBlank()
                    )
                }

                is LoginUserEvent.OnPasswordFocusChange -> {
                    loginState = loginState.copy(
                        isEmailHintVisible = !event.isFocused && loginState.password.isBlank()
                    )
                }
                is LoginUserEvent.OnPasswordIconClicked -> {
                    loginState = loginState.copy(
                        isPasswordVisible = !event.isClicked
                    )
                }
                is LoginUserEvent.OnPasswordEnter -> {
                    val result = onboardingAuthUseCase.validatePassword(event.password)
                    loginState = loginState.copy(
                        password = result.userPassword,
                        validPassword = result.validPassword
                    )
                }
            }
        }
    }

    private fun loginUser() {
        val user = LoginUser(
            email = loginState.emailAddress,
            password = loginState.password
        )
        viewModelScope.launch {
            loginState = loginState.copy(isScreenLoading = true)
            onboardingAuthUseCase
                .loginUserUseCase(user = user)
                .onSuccess { token ->
                    if (!token.isNullOrEmpty()) {
                        loginState = loginState.copy(isScreenLoading = false)
                        _uiEvent.send(
                            LoginUiEvent.Success
                        )
                    } else {
                        loginState = loginState.copy(isScreenLoading = false)
                        _uiEvent.send(
                            LoginUiEvent.ShowSnackbar(
                                UiText.StringResource(R.string.text_error_login_token_missing)
                            )
                        )
                    }
                }
                .onFailure {
                    loginState = loginState.copy(isScreenLoading = false)
                    _uiEvent.send(
                        LoginUiEvent.ShowSnackbar(
                            UiText.StringResource(R.string.text_error_login_failure)
                        )
                    )
                }
        }
    }
}
