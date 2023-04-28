package com.alexafit.agendapresentation.agenda.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.alexafit.agendapresentation.R
import com.alexafit.agendapresentation.agenda.model.AgendaClickEvent
import com.alexafit.agendapresentation.agenda.model.AgendaItemOverview
import com.alexafit.coreui.LocalSpacing

@Composable
fun AgendaItemCard(
    modifier: Modifier = Modifier,
    agendaItemOverview: AgendaItemOverview,
    agendaClickEvent: (AgendaClickEvent) -> Unit
) {
    val checkedState = remember { mutableStateOf(false) }
    val spacing = LocalSpacing.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { }),
        elevation = spacing.spaceSmall,
        backgroundColor = agendaItemOverview.cardColor,
        shape = RoundedCornerShape(spacing.spaceMedium)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .padding(horizontal = spacing.spaceSmall, vertical = spacing.spaceSmall)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                CircleCheckbox(
                    modifier = Modifier
                        .alignByBaseline(),
                    selected = checkedState.value,
                    tint = agendaItemOverview.contrastingColor,
                    onClick = { checkedState.value = !checkedState.value }
                )

                Column(
                    modifier = Modifier
                        .alignByBaseline()
                        .weight(3f)
                        .padding(top = spacing.spaceSmall)
                ) {
                    Text(
                        text = agendaItemOverview.title,
                        style = MaterialTheme.typography.h6,
                        color = agendaItemOverview.contrastingColor,
                        textDecoration = if (checkedState.value) TextDecoration.LineThrough else TextDecoration.None
                    )
                    Spacer(modifier = modifier.height(spacing.spaceExtraSmall))
                    Text(
                        text = agendaItemOverview.subtitle,
                        style = MaterialTheme.typography.body1,
                        color = agendaItemOverview.contrastingColor
                    )
                }
                MoreOptions(
                    tint = agendaItemOverview.contrastingColor,
                    modifier = modifier
                        .alignByBaseline()
                        .weight(0.5f),
                    onClick = { agendaClickEvent(AgendaClickEvent.AgendaItemSettings) }
                )
            }
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Text(
                text = agendaItemOverview.date,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.End,
                modifier = modifier
                    .align(Alignment.End)
                    .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmall),
                color = agendaItemOverview.contrastingColor

            )
        }
    }
}

@Composable
fun CircleCheckbox(
    selected: Boolean,
    tint: Color,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val painter =
        if (selected) R.drawable.baseline_check_circle_outline_24 else R.drawable.baseline_radio_button_unchecked_24
    val background = Color.Transparent
    IconButton(
        onClick = { onClick() },
        modifier = modifier,
        enabled = enabled
    ) {
        Icon(
            painter = painterResource(id = painter),
            tint = tint,
            modifier = modifier.background(background, shape = CircleShape),
            contentDescription = "checkbox"
        )
    }
}

@Composable
fun MoreOptions(
    tint: Color,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    IconButton(
        onClick = { onClick() },
        modifier = modifier,
        enabled = enabled
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_more_horiz_24),
            tint = tint,
            contentDescription = "checkbox"
        )
    }
}
