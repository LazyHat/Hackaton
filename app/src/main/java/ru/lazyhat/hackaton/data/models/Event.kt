package ru.lazyhat.hackaton.data.models

import kotlinx.datetime.LocalDate

data class Event(
    val status: EventStatus,
    val title: String,
    val description: String,
    val date: LocalDate,
    val location: String,
    val organizer: String,
    val content: String,
    val photo: String?,
    val registerDate: LocalDate,
    val teams: String
)