package com.alexafit.onboardingAuthPresentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.alexafit.core.util.UiEvent
import com.alexafit.coreui.LightBlue
import com.alexafit.coreui.LocalSpacing
import com.alexafit.coreui.SuccessGreen
import com.alexafit.coreui.components.buttons.TextActionButton
import com.alexafit.coreui.components.textfield.TextFieldWithIcon
import com.alexafit.onboardingAuthPresentation.R
import com.alexafit.onboardingAuthPresentation.event.navigation.NavigationEvent
import com.alexafit.onboardingAuthPresentation.event.user.LoginUserEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    scaffoldState: ScaffoldState,
    onEvent: (NavigationEvent) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> {
                    keyboardController?.hide()
                    onEvent(NavigationEvent.NavigateToAgenda)
                }
                is UiEvent.ShowSnackbar -> {
                    keyboardController?.hide()
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                is UiEvent.Navigate -> {
                    keyboardController?.hide()
                    onEvent(NavigationEvent.NavigateToRegister)
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.title_welcome_back),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .padding(vertical = spacing.spaceLarge)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        MaterialTheme.colors.surface,
                        shape = RoundedCornerShape(
                            topStart = spacing.spaceLarge,
                            topEnd = spacing.spaceLarge
                        )
                    )
                    .padding(
                        vertical = spacing.spaceLarge,
                        horizontal = spacing.spaceSmall
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                /**
                 * Need to finish TextField api login logic
                 */
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                TextFieldWithIcon(
                    text = viewModel.loginState.emailAddress,
                    keyboardImeAction = if (viewModel.loginState.validPassword) ImeAction.Done else ImeAction.Next,
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier.fillMaxWidth(),
                    onKeyboardActionPressed = {
                        if (viewModel.loginState.validPassword) {
                            keyboardController?.hide()
                            viewModel.onEvent(LoginUserEvent.NavigateToAgenda)
                        }
                    },
                    onValueChange = {
                        viewModel.onEvent(LoginUserEvent.OnEmailAddressEnter(it))
                    },
                    onFocusChanged = { viewModel.onEvent(LoginUserEvent.OnEmailFocusChange(it.isFocused)) },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.text_field_hint_email),
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Light,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier
                        )
                    }
                ) {
                    if (viewModel.loginState.validEmailAddress) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_check_24),
                            contentDescription = stringResource(
                                id = R.string.content_desc_image_vector
                            ),
                            tint = SuccessGreen,
                            modifier = Modifier
                                .height(IntrinsicSize.Min)
                                .width(IntrinsicSize.Min)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                TextFieldWithIcon(
                    text = viewModel.loginState.password,
                    keyboardImeAction = if (viewModel.loginState.validEmailAddress) ImeAction.Done else ImeAction.Next,
                    keyboardType = KeyboardType.Password,
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (viewModel.loginState.isPasswordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation(
                            '●'
                        )
                    },
                    onKeyboardActionPressed = {
                        if (viewModel.loginState.validEmailAddress) {
                            keyboardController?.hide()
                            viewModel.onEvent(LoginUserEvent.NavigateToAgenda)
                        }
                    },
                    onValueChange = { viewModel.onEvent(LoginUserEvent.OnPasswordEnter(it)) },
                    onFocusChanged = { viewModel.onEvent(LoginUserEvent.OnPasswordFocusChange(it.isFocused)) },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.text_field_hint_password),
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Light,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier
                        )
                    }
                ) {
                    IconButton(
                        onClick = { viewModel.onEvent(LoginUserEvent.OnPasswordIconClicked(viewModel.loginState.isPasswordVisible)) },
                        modifier = Modifier
                            .height(IntrinsicSize.Min)
                            .width(IntrinsicSize.Min)
                    ) {
                        Icon(
                            painter = if (viewModel.loginState.isPasswordVisible) {
                                painterResource(
                                    R.drawable.baseline_visibility_24
                                )
                            } else {
                                painterResource(R.drawable.baseline_visibility_off_24)
                            },
                            contentDescription = stringResource(
                                id = R.string.content_desc_image_vector
                            ),
                            modifier = Modifier
                        )
                    }
                }
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                TextActionButton(
                    text = stringResource(id = R.string.text_btn_log_in),
                    textStyle = MaterialTheme.typography.subtitle2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = spacing.spaceExtraSmall)
                ) {
                    viewModel.onEvent(LoginUserEvent.NavigateToAgenda)
                }
            }
        }
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colors.surface)
                .padding(all = spacing.spaceLarge)
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.text_no_account),
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = stringResource(id = R.string.text_sign_up),
                color = LightBlue,
                modifier = Modifier.clickable {
                    onEvent(NavigationEvent.NavigateToRegister)
                }
            )
        }
    }
}
