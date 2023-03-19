package com.alexafit.coreui.components.buttons

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp

@Composable
fun IconActionButton(
    imageVector: ImageVector,
    contentDescription: Int,
    shapeSpacing: Dp,
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
        Icon(imageVector = imageVector, contentDescription = stringResource(id = contentDescription))
    }
}
