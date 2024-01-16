package com.cyberknights4911.scouting.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cyberknights4911.scouting.database.Event
import com.cyberknights4911.scouting.event.EventViewModel

@Composable
fun EventCard(event: Event) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Column {
            Text(
                text = event.name
            )
            Text(
                text = event.city
            )
        }
        Text(
            text = event.startDate,
        )
    }
}

@Preview
@Composable
fun PreviewEventCard() {
    EventCard(event = SampleData.pnwEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Events(
    eventViewModel: EventViewModel = hiltViewModel(),
    initialList: List<Event> = listOf()
) {
    val events by eventViewModel.eventsFlow.collectAsState(initialList)

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Events")
                }
            )
        },
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) {
            items(events) { event ->
                EventCard(event)
            }
        }
    }
}

@Preview
@Composable
fun PreviewEvents() {
    Events(initialList = List(10) { SampleData.pnwEvent })
}
