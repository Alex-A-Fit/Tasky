package com.alexafit.onboardingAuthPresentation.register

import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.alexafit.coreui.LocalSpacing
import com.alexafit.coreui.SuccessGreen
import com.alexafit.coreui.components.buttons.TextActionButton
import com.alexafit.coreui.components.loading.CircularLoadingDialog
import com.alexafit.coreui.components.textfield.TextFieldWithIcon
import com.alexafit.onboardingAuthPresentation.R
import com.alexafit.onboardingAuthPresentation.event.navigation.NavigationEvent
import com.alexafit.onboardingAuthPresentation.event.user.RegisterUserEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    scaffoldState: ScaffoldState,
    onEvent: (NavigationEvent) -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> {
                    keyboardController?.hide()
                    onEvent(NavigationEvent.NavigateToRegister)
                }
                is UiEvent.ShowSnackbar -> {
                    keyboardController?.hide()
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                is UiEvent.Navigate -> {
                    keyboardController?.hide()
                    onEvent(NavigationEvent.NavigateToLogin)
                }
                is UiEvent.Loading -> {
                    keyboardController?.hide()
                    isLoading = true
                }
            }
        }
    }
    /**
     * Finish involving UI with data layer in another PR
     */
    if (isLoading) {
        CircularLoadingDialog()
    } else {
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
                text = stringResource(id = R.string.title_create_account),
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
                        horizontal = spacing.spaceMedium
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                TextFieldWithIcon(
                    text = viewModel.registerState.name,
                    keyboardImeAction = if (viewModel.registerState.validPassword && viewModel.registerState.validEmailAddress) ImeAction.Done else ImeAction.Next,
                    modifier = Modifier.fillMaxWidth(),
                    onKeyboardActionPressed = {
                        if (viewModel.registerState.validPassword && viewModel.registerState.validEmailAddress) {
                            keyboardController?.hide()
                            viewModel.onEvent(RegisterUserEvent.NavigateToAgenda)
                        }
                    },
                    onValueChange = {
                        viewModel.onEvent(RegisterUserEvent.OnNameEnter(it))
                    },
                    onFocusChanged = { viewModel.onEvent(RegisterUserEvent.OnNameFocusChange(it.isFocused)) },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.text_field_hint_name),
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Light,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier
                        )
                    }
                ) {
                    if (viewModel.registerState.validName) {
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
                    text = viewModel.registerState.emailAddress,
                    keyboardImeAction = if (viewModel.registerState.validPassword && viewModel.registerState.validName) ImeAction.Done else ImeAction.Next,
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier.fillMaxWidth(),
                    onKeyboardActionPressed = {
                        if (viewModel.registerState.validPassword && viewModel.registerState.validName) {
                            keyboardController?.hide()
                            viewModel.onEvent(RegisterUserEvent.NavigateToAgenda)
                        }
                    },
                    onValueChange = {
                        viewModel.onEvent(RegisterUserEvent.OnEmailAddressEnter(it))
                    },
                    onFocusChanged = { viewModel.onEvent(RegisterUserEvent.OnEmailFocusChange(it.isFocused)) },
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
                    if (viewModel.registerState.validEmailAddress) {
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
                    text = viewModel.registerState.password,
                    keyboardImeAction = if (viewModel.registerState.validEmailAddress && viewModel.registerState.validName) ImeAction.Done else ImeAction.Next,
                    keyboardType = KeyboardType.Password,
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (viewModel.registerState.isPasswordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation(
                            '●'
                        )
                    },
                    onKeyboardActionPressed = {
                        if (viewModel.registerState.validEmailAddress && viewModel.registerState.validName) {
                            keyboardController?.hide()
                            viewModel.onEvent(RegisterUserEvent.NavigateToAgenda)
                        }
                    },
                    onValueChange = { viewModel.onEvent(RegisterUserEvent.OnPasswordEnter(it)) },
                    onFocusChanged = { viewModel.onEvent(RegisterUserEvent.OnPasswordFocusChange(it.isFocused)) },
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
                        onClick = { viewModel.onEvent(RegisterUserEvent.OnPasswordIconClicked(viewModel.registerState.isPasswordVisible)) },
                        modifier = Modifier
                    ) {
                        Icon(
                            painter = if (viewModel.registerState.isPasswordVisible) {
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
                    viewModel.onEvent(RegisterUserEvent.NavigateToAgenda)
                }
            }
        }
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colors.surface)
                .padding(start = spacing.spaceMedium, end = spacing.spaceMedium, bottom = spacing.spaceExtraLarge)
                .align(Alignment.BottomStart),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = {
                    onEvent(NavigationEvent.NavigateToLogin)
                },
                modifier = Modifier.background(
                    MaterialTheme.colors.primary,
                    RoundedCornerShape(spacing.spaceMedium)
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_chevron_left_24),
                    contentDescription = stringResource(id = R.string.content_desc_left_chevron),
                    tint = Color.White,
                    modifier = Modifier
                        .width(spacing.spaceLarge)
                        .height(spacing.spaceLarge)
                )
            }
        }
    }
}
