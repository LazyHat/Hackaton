package ru.lazyhat.hackaton.ui.pages.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.datetime.toJavaLocalDateTime
import org.koin.androidx.compose.koinViewModel
import ru.lazyhat.hackaton.data.models.News
import ru.lazyhat.hackaton.di.startKoin
import ru.lazyhat.hackaton.ui.utils.ComingSoon
import java.time.format.DateTimeFormatter

@Composable
fun NewsPage(viewModel: NewsPageViewModel = koinViewModel()) {

    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(uiState) {
            NewsCard(state = it)
        }
    }
}

@Composable
fun NewsCard(state: News) = state.let {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(5.dp)) {
            Text(it.title, fontSize = 20.sp)
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                thickness = 1.dp,
                color = Color.Black
            )
            Text(it.content, fontSize = 15.sp)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
            it.photo?.let {
                AsyncImage(model = it, contentDescription = null)
            }
            Text("Author: ${it.author}")
            Text(
                "created at: ${
                    it.datePublished.toJavaLocalDateTime().format(
                        DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm")
                    )
                }"
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton({
                    ComingSoon(context)
                }) {
                    Icon(
                        if (it.isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        null
                    )
                }
                Text(it.likes.toString())
            }
        }
    }
}

@Preview
@Composable
fun PreviewNews() {
    startKoin()
    NewsPage()
}
