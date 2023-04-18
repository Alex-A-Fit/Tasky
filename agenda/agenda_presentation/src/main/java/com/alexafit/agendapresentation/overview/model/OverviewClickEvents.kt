package com.alexafit.agendapresentation.overview.model

import java.time.LocalDate

sealed class OverviewClickEvents {
    data class OnDialogSelection(val dialogDate: LocalDate) : OverviewClickEvents()
    data class OnOverviewDateSelected(val selectedDate: LocalDate) : OverviewClickEvents()
}
