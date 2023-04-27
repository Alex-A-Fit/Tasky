package com.alexafit.agendapresentation.agenda

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexafit.agendapresentation.agenda.model.AgendaClickEvent
import com.alexafit.agendapresentation.agenda.model.AgendaState
import com.alexafit.agendapresentation.agenda.model.Day
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AgendaViewModel @Inject constructor() : ViewModel() {
    var agendaState by mutableStateOf(AgendaState())
        private set

    init {
        updateSixDayOverview()
    }
    fun onClickEvent(clickEvent: AgendaClickEvent) {
        viewModelScope.launch {
            when (clickEvent) {
                is AgendaClickEvent.OnDialogSelection -> {
                    agendaState = agendaState.copy(
                        startingDate = clickEvent.dialogDate,
                        currentMonth = clickEvent.dialogDate.month.name,
                        chosenDate = clickEvent.dialogDate
                    )
                    updateSixDayOverview(startingDate = clickEvent.dialogDate)
                }
                is AgendaClickEvent.OnAgendaDateSelected -> {
                    agendaState = agendaState.copy(
                        chosenDate = clickEvent.selectedDate
                    )
                }
                AgendaClickEvent.AgendaItemSettings -> Unit
            }
        }
    }

    private fun updateSixDayOverview(startingDate: LocalDate? = null) {
        val startDate = startingDate ?: agendaState.startingDate
        val listOfSixConsecutiveDays = mutableListOf<Day>()
        repeat(6) { dayCount ->
            val date = startDate.plusDays(dayCount.toLong())
            val day = Day(
                dayAcronym = date.dayOfWeek.name.first().uppercaseChar().toString(),
                dayOfMonth = date.dayOfMonth.toString(),
                localDate = date
            )
            listOfSixConsecutiveDays.add(day)
        }
        agendaState = agendaState.copy(day = listOfSixConsecutiveDays)
    }
}
