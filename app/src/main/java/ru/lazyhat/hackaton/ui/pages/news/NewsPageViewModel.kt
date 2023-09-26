package ru.lazyhat.hackaton.ui.pages.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ru.lazyhat.hackaton.data.repos.MainRepository

class NewsPageViewModel(
    mainRepository: MainRepository
) : ViewModel() {
    val uiState = mainRepository.getNews()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), listOf())
}