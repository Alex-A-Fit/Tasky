package com.alexafit.core_ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun IconActionButton(
    imageVector: ImageVector,
    contentDescription: Int,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(100.dp)
    ) {
        Icon(imageVector = imageVector , contentDescription = stringResource(id = contentDescription))
    }
}