package com.alexafit.agendapresentation.overview.model

import java.time.LocalDate

data class OverviewState(
    var startingDate: LocalDate = LocalDate.now(),
    var chosenDate: LocalDate = LocalDate.now(),
    var currentMonth: String = chosenDate.month.name,
    var overviewCalendar: MutableList<OverviewCalendar> = mutableListOf()
)
