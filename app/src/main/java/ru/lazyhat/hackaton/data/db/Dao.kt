package ru.lazyhat.hackaton.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MainDao {
    @Query("SELECT * FROM news")
    fun getNews(): List<News>

    @Insert
    fun insertNews(news: News)
}