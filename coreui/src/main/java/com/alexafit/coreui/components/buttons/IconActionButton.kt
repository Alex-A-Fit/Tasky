package com.alexafit.coreui.components.buttons

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp

@Composable
fun IconActionButton(
    image: Painter,
    contentDescription: Int,
    shapeSpacing: Dp,
    iconTint: Color,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(shapeSpacing)
    ) {
        Icon(
            painter = image,
            contentDescription = stringResource(id = contentDescription),
            tint = iconTint,
            modifier = modifier
        )
    }
}
