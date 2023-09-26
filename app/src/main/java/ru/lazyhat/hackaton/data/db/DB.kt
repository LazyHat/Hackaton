package ru.lazyhat.hackaton.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Event::class, News::class, Like::class, User::class, Comment::class],
    version = 1
)
abstract class DB : RoomDatabase() {
    abstract fun mainDao(): MainDao
}