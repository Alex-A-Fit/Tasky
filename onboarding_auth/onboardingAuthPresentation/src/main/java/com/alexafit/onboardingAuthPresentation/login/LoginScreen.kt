package com.alexafit.onboardingAuthPresentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
import com.alexafit.onboardingAuthPresentation.login.event.LoginEvent

@Composable
fun LoginScreen(
    scaffoldState: ScaffoldState,
    onEvent: (LoginEvent) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val textVisibility = remember {
        mutableStateOf(true)
    }
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onEvent(LoginEvent.Login)
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
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
                 * Need to finish TextField api logic and on focus change logic
                 */
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                TextFieldWithIcon(
                    text = viewModel.emailAddress,
                    hint = stringResource(id = R.string.text_field_hint_email),
                    keyboardImeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email,
                    modifier = Modifier,
                    onValueChange = viewModel::onEmailAddressEnter,
                    onFocusChanged = {},
                    iconImage = if (viewModel.validEmailAddress) R.drawable.baseline_check_24 else null,
                    iconContentDescription = R.string.content_desc_image_vector,
                    iconTint = SuccessGreen,
                    iconOnClick = {}
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                TextFieldWithIcon(
                    text = viewModel.password,
                    hint = stringResource(id = R.string.text_field_hint_password),
                    keyboardImeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password,
                    modifier = Modifier,
                    iconImage = if (textVisibility.value) R.drawable.baseline_visibility_24 else R.drawable.baseline_visibility_off_24,
                    iconOnClick = { textVisibility.value = !textVisibility.value },
                    iconContentDescription = R.string.content_desc_image_vector,
                    visualTransformation = if (textVisibility.value) VisualTransformation.None else PasswordVisualTransformation('●'),
                    onValueChange = viewModel::onPasswordEnter,
                    onFocusChanged = {}
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                TextActionButton(
                    text = stringResource(id = R.string.text_btn_log_in),
                    textStyle = MaterialTheme.typography.subtitle2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = spacing.spaceExtraSmall)
                ) {
                    viewModel.onEvent(LoginEvent.Login)
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
                    onEvent(LoginEvent.Register)
                }
            )
        }
    }
}
