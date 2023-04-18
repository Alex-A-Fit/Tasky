package com.alexafit.agendapresentation.overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexafit.agendapresentation.overview.model.OverviewCalendar
import com.alexafit.agendapresentation.overview.model.OverviewClickEvents
import com.alexafit.agendapresentation.overview.model.OverviewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor() : ViewModel() {
    var overviewState by mutableStateOf(OverviewState())
        private set

    init {
        updateSixDayOverview()
    }
    fun onClickEvent(clickEvent: OverviewClickEvents) {
        viewModelScope.launch {
            when (clickEvent) {
                is OverviewClickEvents.OnDialogSelection -> {
                    overviewState = overviewState.copy(
                        startingDate = clickEvent.dialogDate,
                        currentMonth = clickEvent.dialogDate.month.name,
                        chosenDate = clickEvent.dialogDate
                    )
                    updateSixDayOverview(startingDate = clickEvent.dialogDate)
                }
                is OverviewClickEvents.OnOverviewDateSelected -> {
                    overviewState = overviewState.copy(
                        chosenDate = clickEvent.selectedDate
                    )
                }
            }
        }
    }

    private fun updateSixDayOverview(startingDate: LocalDate? = null) {
        val startDate = startingDate ?: overviewState.startingDate
        val listOfSixConsecutiveDays = mutableListOf<OverviewCalendar>()
        repeat(6) { dayCount ->
            val date = startDate.plusDays(dayCount.toLong())
            val overviewCalendar = OverviewCalendar(
                dayAcronym = date.dayOfWeek.name.first().uppercaseChar().toString(),
                dayOfMonth = date.dayOfMonth.toString(),
                localDate = date
            )
            listOfSixConsecutiveDays.add(overviewCalendar)
        }
        overviewState = overviewState.copy(overviewCalendar = listOfSixConsecutiveDays)
    }
}
