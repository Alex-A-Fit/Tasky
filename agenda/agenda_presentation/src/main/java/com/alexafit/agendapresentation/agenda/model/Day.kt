package com.alexafit.agendapresentation.agenda.model

import java.time.LocalDate

data class Day(
    val dayAcronym: String,
    val dayOfMonth: String,
    val localDate: LocalDate
)
