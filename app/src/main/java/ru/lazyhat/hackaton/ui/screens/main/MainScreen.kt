package ru.lazyhat.hackaton.ui.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.lazyhat.hackaton.di.startKoin
import ru.lazyhat.hackaton.ui.pages.events.EventsPage
import ru.lazyhat.hackaton.ui.pages.news.NewsPage
import ru.lazyhat.hackaton.ui.theme.HackatonTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val pagerState = rememberPagerState(initialPage = 1) { 2 }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    when (pagerState.currentPage) {
                        0 -> "News"
                        1 -> "Events"
                        else -> "error"
                    }
                )
            })
        },
        bottomBar = {
            TabsBar(
                selectedIndex = pagerState.currentPage,
                onSelect = { scope.launch { pagerState.animateScrollToPage(it) } })
        }
    ) { padding ->
        HorizontalPager(
            modifier = Modifier
                .padding(5.dp)
                .padding(padding),
            state = pagerState
        ) {
            when (it) {
                0 -> NewsPage()
                1 -> EventsPage()
            }
        }
    }
}

@Composable
fun TabsBar(selectedIndex: Int, onSelect: (index: Int) -> Unit) {
    TabRow(selectedTabIndex = selectedIndex) {
        Tab(selected = selectedIndex == 0, onClick = { onSelect(0) }) {
            Text("News")
        }
        Tab(selected = selectedIndex == 1, onClick = { onSelect(1) }) {
            Text("Events")
        }
    }
}



@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun Preview() {
    startKoin()
    HackatonTheme {
        MainScreen()
    }
}