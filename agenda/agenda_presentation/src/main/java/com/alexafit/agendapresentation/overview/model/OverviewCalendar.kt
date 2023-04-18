package com.alexafit.agendapresentation.overview.model

import java.time.LocalDate

data class OverviewCalendar(
    val dayAcronym: String,
    val dayOfMonth: String,
    val localDate: LocalDate
)
