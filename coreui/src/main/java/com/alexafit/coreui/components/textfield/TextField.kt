package com.alexafit.coreui.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.alexafit.coreui.LocalSpacing
import com.alexafit.coreui.R
import com.alexafit.coreui.components.buttons.IconActionButton

@Composable
fun TextField(
    text: String,
    hint: String,
    keyboardImeAction: ImeAction,
    iconVector: ImageVector?,
    iconVectorDescription: Int?,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = false,
    isIconClickable: Boolean = false,
    onValueChange: (String) -> Unit,
    iconOnClick: () -> Unit,
    onFocusChanged: (FocusState) -> Unit
) {
    val spacing = LocalSpacing.current
    Box(modifier = modifier) {
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
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(spacing.cornerShapeMedium))
                .padding(spacing.spaceSmallest)
                .shadow(
                    elevation = spacing.shadowSmall,
                    shape = RoundedCornerShape(spacing.cornerShapeMedium)
                )
                .background(MaterialTheme.colors.secondaryVariant)
                .fillMaxWidth()
                .padding(all = spacing.spaceMedium)
                .onFocusChanged { onFocusChanged(it) }
        )
        if (isHintVisible) {
            Text(
                text = hint,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = spacing.spaceMedium)
            )
        }
        iconVector?.let {
            IconActionButton(
                imageVector = iconVector,
                contentDescription = iconVectorDescription ?: R.string.content_desc_image_vector,
                shapeSpacing = spacing.default
            ) {
                if (isIconClickable) iconOnClick()
            }
        }
    }
}
