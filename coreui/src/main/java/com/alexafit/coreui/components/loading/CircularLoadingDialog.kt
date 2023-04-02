package com.alexafit.coreui.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.alexafit.coreui.LocalSpacing

@Composable
fun CircularLoadingDialog(
    modifier: Modifier
) {
    val spacing = LocalSpacing.current
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .background(Color.DarkGray.copy(alpha = 0.5f))
                .padding(spacing.spaceMedium)
                .size(spacing.spaceExtraLarge)
                .clip(CircleShape)
        ) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = modifier
                    .size(spacing.spaceLarge)
            )
        }
    }
}
