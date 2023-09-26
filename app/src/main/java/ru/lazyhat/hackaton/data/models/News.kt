package ru.lazyhat.hackaton.data.models

import kotlinx.datetime.LocalDateTime

data class News(
    val title: String,
    val content: String,
    val author: String,
    val datePublished: LocalDateTime,
    val photo: String?,
    val likes: UInt,
    val isLiked: Boolean
)