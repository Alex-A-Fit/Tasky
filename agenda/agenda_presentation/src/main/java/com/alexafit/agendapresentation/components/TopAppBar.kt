package com.alexafit.agendapresentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alexafit.coreui.LocalSpacing

@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    leftAlignedComposable: @Composable (() -> Unit)? = null,
    centerAlignedComposable: @Composable (() -> Unit)? = null,
    rightAlignedComposable: @Composable (() -> Unit)? = null
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceExtraSmall),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        leftAlignedComposable?.invoke()
        centerAlignedComposable?.invoke()
        rightAlignedComposable?.invoke()
    }
}
