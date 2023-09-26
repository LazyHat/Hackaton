package ru.lazyhat.hackaton

import Fake
import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidLogger
import ru.lazyhat.hackaton.data.db.DB
import ru.lazyhat.hackaton.di.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this) {
            androidLogger()
        }
        val db by inject<DB>()
        CoroutineScope(Dispatchers.IO).launch {
            if (db.mainDao().getNews().isEmpty())
                Fake.news.forEach {
                    //db.mainDao().insertNews(it)
                }
        }
    }
}