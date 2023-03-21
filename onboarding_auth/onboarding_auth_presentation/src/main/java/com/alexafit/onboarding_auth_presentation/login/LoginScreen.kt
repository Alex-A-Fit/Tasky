package com.alexafit.onboarding_auth_presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.alexafit.core.util.UiEvent
import com.alexafit.coreui.LocalSpacing
import com.alexafit.coreui.components.buttons.TextActionButton
import com.alexafit.coreui.components.textfield.TextField
import com.alexafit.onboarding_auth_presentation.R
import com.alexafit.onboarding_auth_presentation.login.event.LoginEvent

@Composable
fun LoginScreen(
    scaffoldState: ScaffoldState,
    onEventClick: (LoginEvent) -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onEventClick(LoginEvent.Login)
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.title_welcome_back),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .padding(vertical = spacing.spaceMedium)
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
                    all = spacing.spaceSmall
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Need to finish designing
            Spacer(modifier = Modifier.height(spacing.spaceLarge))
            TextField(
                text = "",
                hint = stringResource(id = R.string.text_field_hint_email),
                keyboardImeAction = ImeAction.Next,
                iconVector = null,
                iconVectorDescription = null,
                modifier = Modifier,
                isHintVisible = true,
                isIconClickable = false,
                onValueChange = {},
                iconOnClick = {},
                onFocusChanged = {}
            )
            TextField(
                text = "",
                hint = stringResource(id = R.string.text_field_hint_password),
                keyboardImeAction = ImeAction.Next,
                iconVector = null,
                iconVectorDescription = null,
                modifier = Modifier,
                isHintVisible = true,
                isIconClickable = false,
                onValueChange = {},
                iconOnClick = {},
                onFocusChanged = {}
            )

            TextActionButton(
                text = stringResource(id = R.string.text_btn_log_in),
                textStyle = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = spacing.spaceExtraSmall)
            ) {
                // needs action implemented
            }
        }
    }
}
