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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyberknights4911.scouting.match.MatchWithTeams

@Composable
fun MatchCard(matchWithTeams: MatchWithTeams) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = matchWithTeams.redTeams.map {
                    it.teamNumber
                }.joinToString(", ")
            )
            Text(
                text = matchWithTeams.blueTeams.map {
                    it.teamNumber
                }.joinToString(", ")
            )
        }
        Column {
            Text(
                text = when (matchWithTeams.compLevel) {
                    "f" -> "Final"
                    "qm" -> "Qualifier"
                    "qf" -> "Quarterfinal"
                    "sf" -> "Semifinal"
                    "ef" -> "Elimination"
                    else -> "Unknown"
                }
            )
            Text(
                text = "Match ${matchWithTeams.matchNumber} Set ${matchWithTeams.setNumber}"
            )
        }
    }
}

@Preview
@Composable
fun PreviewMatchCard() {
    MatchCard(SampleData.matchWithTeams)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Matches(matches: List<MatchWithTeams>) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Matches")
                }
            )
        },
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding) {
            items(matches) { match ->
                MatchCard(match)
            }
        }
    }
}

@Preview
@Composable
fun PreviewMatches() {
    Matches(List(15) { SampleData.matchWithTeams })
}
