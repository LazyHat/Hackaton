package ru.lazyhat.hackaton.data.repos

import Fake
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.lazyhat.hackaton.data.models.Event
import ru.lazyhat.hackaton.data.models.News

interface MainRepository {
    fun getNews(): Flow<List<News>>
    fun getEvents(): Flow<List<Event>>
}

class MainRepositoryImpl : MainRepository{
    override fun getNews(): Flow<List<News>> {
        return MutableStateFlow(Fake.news)
    }

    override fun getEvents(): Flow<List<Event>> {
        return MutableStateFlow(Fake.events)
    }
}