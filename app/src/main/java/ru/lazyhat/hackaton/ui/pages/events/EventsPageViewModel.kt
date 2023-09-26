package ru.lazyhat.hackaton.ui.pages.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ru.lazyhat.hackaton.data.repos.MainRepository

class EventsPageViewModel(
    mainRepository: MainRepository
) : ViewModel() {
    val uiState = mainRepository.getEvents()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), listOf())
}