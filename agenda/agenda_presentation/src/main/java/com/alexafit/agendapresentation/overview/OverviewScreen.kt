package com.alexafit.agendapresentation.overview

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.alexafit.agendapresentation.overview.components.DatePickerDialog
import com.alexafit.agendapresentation.overview.components.OverviewCalendarDate
import com.alexafit.agendapresentation.overview.components.ProfileIcon
import com.alexafit.agendapresentation.overview.model.OverviewClickEvents
import com.alexafit.coreui.LocalSpacing

@Composable
fun OverviewScreen(
    scaffoldState: ScaffoldState,
//    onEvent: () -> Unit,
    viewModel: OverviewViewModel = hiltViewModel()
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
                    overviewState = viewModel.overviewState,
                    onDateSelectedClickEvent = {
                        viewModel.onClickEvent(OverviewClickEvents.OnDialogSelection(it))
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
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceMedium, vertical = spacing.spaceSmall),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    repeat(6) { dayCount ->
                        val date = viewModel.overviewState.overviewCalendar
                        OverviewCalendarDate(
                            isSelected = viewModel.overviewState.chosenDate == date[dayCount].localDate,
                            dayAcronym = date[dayCount].dayAcronym,
                            dayOfMonth = date[dayCount].dayOfMonth,
                            onDateSelectedClickEvent = {
                                viewModel.onClickEvent(OverviewClickEvents.OnOverviewDateSelected(date[dayCount].localDate))
                            },
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}
