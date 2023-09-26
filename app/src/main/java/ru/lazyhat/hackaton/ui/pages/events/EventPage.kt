package ru.lazyhat.hackaton.ui.pages.events

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.datetime.toJavaLocalDate
import org.koin.androidx.compose.koinViewModel
import ru.lazyhat.hackaton.data.models.Event
import ru.lazyhat.hackaton.data.models.EventStatus
import ru.lazyhat.hackaton.di.startKoin
import ru.lazyhat.hackaton.ui.utils.ComingSoon
import java.time.format.DateTimeFormatter

@Composable
fun EventsPage(viewModel: EventsPageViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(uiState) {
            EventCard(state = it)
        }
    }
}

@Composable
fun EventCard(state: Event) = state.let {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(5.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(it.title, fontSize = 20.sp)
                val cardColor = when (it.status) {
                    EventStatus.Active -> Color(0xFF8888FF)
                    EventStatus.Registering -> Color(0xFF77FF77)
                    EventStatus.Archived -> Color.Gray
                }
                Card(border = BorderStroke(2.dp, cardColor), shape = RoundedCornerShape(6.dp)) {
                    Text(
                        modifier = Modifier
                            .padding(3.dp)
                            .padding(horizontal = 3.dp),
                        text = it.status.name,
                        color = cardColor
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                thickness = 1.dp,
                color = Color.Black
            )
            Text(it.description)
            Text(it.content, fontSize = 15.sp)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
            it.photo?.let {
                AsyncImage(model = it, contentDescription = null)
            }
            Text(
                "starting at: ${
                    it.date.toJavaLocalDate().format(
                        DateTimeFormatter.ofPattern("dd:MM:yyyy")
                    )
                }"
            )
            Text(
                "registering at: ${
                    it.registerDate.toJavaLocalDate().format(
                        DateTimeFormatter.ofPattern("dd:MM:yyyy")
                    )
                }"
            )
            Text("Organizer: ${it.organizer}")
            Text("Location: ${it.location}")
            Text("Teams: ${it.teams}")
            if (it.status == EventStatus.Registering)
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        ComingSoon(context)
                    }) {
                    Text("Register")
                }
        }
    }
}

@Preview
@Composable
fun PreviewEvents() {
    startKoin()
    EventsPage()
}