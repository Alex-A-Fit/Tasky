package com.alexafit.agendapresentation.agenda.model

import java.time.LocalDate

sealed class AgendaClickEvents {
    data class OnDialogSelection(val dialogDate: LocalDate) : AgendaClickEvents()
    data class OnAgendaDateSelected(val selectedDate: LocalDate) : AgendaClickEvents()
}
