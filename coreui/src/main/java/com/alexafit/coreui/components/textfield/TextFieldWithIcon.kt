package com.alexafit.coreui.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.alexafit.coreui.LocalSpacing

@Composable
fun TextFieldWithIcon(
    text: String,
    keyboardImeAction: ImeAction,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    onKeyboardActionPressed: (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit,
    placeholder: @Composable (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null
) {
    val spacing = LocalSpacing.current
    TextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier
            .shadow(
                elevation = spacing.shadowSmall,
                shape = RoundedCornerShape(spacing.cornerShapeMedium)
            )
            .background(
                color = MaterialTheme.colors.secondaryVariant
            )
            .fillMaxWidth()
            .onFocusChanged { onFocusChanged(it) },
        trailingIcon = icon,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            imeAction = keyboardImeAction,
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onKeyboardActionPressed?.invoke()
                defaultKeyboardAction(keyboardImeAction)
            },
            onSearch = {
                onKeyboardActionPressed?.invoke()
                defaultKeyboardAction(keyboardImeAction)
            },
            onNext = {
                onKeyboardActionPressed?.invoke()
                defaultKeyboardAction(keyboardImeAction)
            }
        ),
        singleLine = true,
        textStyle = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Start),
        shape = RoundedCornerShape(spacing.spaceMedium),
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onSecondary,
            placeholderColor = MaterialTheme.colors.onSurface
        ),
        placeholder = placeholder
    )
}
