package ru.lazyhat.hackaton.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import ru.lazyhat.hackaton.data.models.EventStatus

@Entity
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val content: String,
    val authorId: Int,
    val datePublished: LocalDateTime,
    val photoUrl: String?
)

@Entity
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val status: EventStatus,
    val title: String,
    val description: String,
    val date: LocalDate,
    val location: String,
    val organizerId: Int,
    val content: String,
    val photoUrl: String?,
    val tags: String,
    val registerDate: LocalDate,
    val teams: String
)

@Entity
data class Comment(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: Int,
    val newsId: Int,
    val content: String,
    val createdAt: LocalDateTime,
    val likes: Int
)

@Entity
data class Like(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: Int,
    val newsId: Int,
    val parentId: Int?
)

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username: String,
    val password: String
)