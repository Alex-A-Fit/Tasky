package com.alexafit.agendapresentation.agenda

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.alexafit.agendapresentation.R
import com.alexafit.agendapresentation.agenda.components.AgendaDate
import com.alexafit.agendapresentation.agenda.components.AgendaItemCard
import com.alexafit.agendapresentation.agenda.components.DatePickerDialog
import com.alexafit.agendapresentation.agenda.components.ProfileIcon
import com.alexafit.agendapresentation.agenda.model.AgendaClickEvent
import com.alexafit.agendapresentation.agenda.model.AgendaItemOverview
import com.alexafit.coreui.LocalSpacing

@Composable
fun AgendaScreen(
    scaffoldState: ScaffoldState,
//    onEvent: () -> Unit,
    viewModel: AgendaViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceExtraSmall),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DatePickerDialog(
                    agendaState = viewModel.agendaState,
                    onDateSelectedClickEvent = {
                        viewModel.onClickEvent(AgendaClickEvent.OnDialogSelection(it))
                    }
                )
                ProfileIcon()
            }
            Spacer(modifier = Modifier.height(spacing.spaceSmall))
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
                    .padding(top = spacing.spaceSmall),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmall),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    repeat(6) { dayCount ->
                        val date = viewModel.agendaState.day
                        AgendaDate(
                            isSelected = viewModel.agendaState.chosenDate == date[dayCount].localDate,
                            dayAcronym = date[dayCount].dayAcronym,
                            dayOfMonth = date[dayCount].dayOfMonth,
                            onDateSelectedClickEvent = {
                                viewModel.onClickEvent(AgendaClickEvent.OnAgendaDateSelected(date[dayCount].localDate))
                            },
                            modifier = Modifier
                        )
                    }
                }
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                Column(modifier = Modifier.padding(horizontal = spacing.spaceMedium)) {
                    Text(
                        stringResource(id = R.string.text_agenda_today_title),
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.onSecondary,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.height(spacing.spaceMedium))
                    AgendaItemCard(
                        agendaItemOverview = AgendaItemOverview(
                            "Test Title",
                            subtitle = "Test Subtitle",
                            date = "Test DATE",
                            cardColor = Color.LightGray
                        ),
                        agendaClickEvent = { viewModel.onClickEvent(AgendaClickEvent.AgendaItemSettings) },
                        modifier = Modifier
                    )
                }
            }
        }
    }
}
