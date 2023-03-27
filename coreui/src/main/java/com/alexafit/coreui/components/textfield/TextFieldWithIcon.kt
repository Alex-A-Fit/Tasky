package com.alexafit.coreui.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.alexafit.coreui.LocalSpacing
import com.alexafit.coreui.R

@Composable
fun TextFieldWithIcon(
    text: String,
    hint: String,
    keyboardImeAction: ImeAction,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    iconImage: Int? = null,
    iconContentDescription: Int? = null,
    iconTint: Color = MaterialTheme.colors.onSurface,
    iconOnClick: () -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit
) {
    val spacing = LocalSpacing.current

    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = {
                    defaultKeyboardAction(imeAction = keyboardImeAction)
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = keyboardImeAction,
                keyboardType = keyboardType
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(spacing.cornerShapeMedium))
                .shadow(
                    elevation = spacing.shadowSmall,
                    shape = RoundedCornerShape(spacing.cornerShapeMedium)
                )
                .background(
                    color = MaterialTheme.colors.secondaryVariant
                )
                .fillMaxWidth()
                .padding(spacing.spaceMedium)
                .padding(end = spacing.spaceExtraLarge)
                .onFocusChanged { onFocusChanged(it) },
            textStyle = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Start),
            visualTransformation = visualTransformation
        )
        if (text.isEmpty()) {
            Text(
                text = hint,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(spacing.spaceMedium)
            )
        }
        iconImage?.let {
            IconButton(
                onClick = { iconOnClick() },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .height(IntrinsicSize.Min)
                    .width(IntrinsicSize.Min)
            ) {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = stringResource(
                        id = iconContentDescription ?: R.string.content_desc_image_vector
                    ),
                    tint = iconTint
                )
            }
        }
    }
}
