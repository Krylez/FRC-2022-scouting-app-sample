package com.cyberknights4911.scouting.compose

import android.util.Base64
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.cyberknights4911.scouting.team.TeamWithLogo

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TeamCard(team: TeamWithLogo) {
    Row(
        modifier = Modifier.padding(all = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            model = team.logo,
            contentDescription = team.team.nickname,
            modifier = Modifier
                .height(48.dp)
                .width(48.dp)
        ) { requestBuilder ->
            requestBuilder.load(Base64.decode(team.logo, Base64.DEFAULT))
        }
        Column {
            Text(
                text = team.team.teamNumber.toString()
            )
            Text(
                text = team.team.nickname
            )
        }
    }
}

@Preview
@Composable
fun PreviewTeamCard() {
    TeamCard(team = SampleData.teamWithLogo)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamCards(
    redTeams: List<TeamWithLogo>,
    blueTeams: List<TeamWithLogo>
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Balance")
                }
            )
        },
    ) { innerPadding ->
        Row(
            modifier = Modifier.fillMaxWidth().padding(innerPadding),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                redTeams.forEach { team ->
                    TeamCard(team)
                }
            }
            Column {
                blueTeams.forEach { team ->
                    TeamCard(team)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewTeamCards() {
    TeamCards(
        redTeams = List(3) { SampleData.teamWithLogo },
        blueTeams = List(3) { SampleData.teamWithLogo }
    )
}
