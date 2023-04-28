package com.alexafit.agendapresentation.agenda.model

import java.time.LocalDate

sealed class AgendaClickEvent {
    data class OnDialogSelection(val dialogDate: LocalDate) : AgendaClickEvent()
    data class OnAgendaDateSelected(val selectedDate: LocalDate) : AgendaClickEvent()
    object AgendaItemSettings : AgendaClickEvent()
}
