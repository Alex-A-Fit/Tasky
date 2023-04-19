package com.alexafit.agendapresentation.agenda.model

import java.time.LocalDate

data class AgendaState(
    var startingDate: LocalDate = LocalDate.now(),
    var chosenDate: LocalDate = LocalDate.now(),
    var currentMonth: String = chosenDate.month.name,
    var day: MutableList<Day> = mutableListOf()
)
