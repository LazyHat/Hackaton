package ru.lazyhat.hackaton.di

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.lazyhat.hackaton.data.db.DB
import ru.lazyhat.hackaton.data.repos.MainRepository
import ru.lazyhat.hackaton.data.repos.MainRepositoryImpl
import ru.lazyhat.hackaton.ui.pages.events.EventsPageViewModel
import ru.lazyhat.hackaton.ui.pages.news.NewsPageViewModel

private val mainModule = module {
    single<MainRepository> {
        MainRepositoryImpl()
    }
    single {
        Room.databaseBuilder(androidContext(), DB::class.java, "main-database").build()
    }
    viewModel {
        NewsPageViewModel(get())
    }
    viewModel {
        EventsPageViewModel(get())
    }
}

fun startKoin(context: Context, block: KoinApplication.() -> Unit) = startKoin {
    androidContext(context)
    modules(mainModule)
    block()
}

fun startKoin(context: Context) =
    startKoin(context) {}

@Composable
fun startKoin() = startKoin(LocalContext.current)