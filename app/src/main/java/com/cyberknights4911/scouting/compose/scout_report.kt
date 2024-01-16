package com.cyberknights4911.scouting.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyberknights4911.scouting.GamePiece
import com.cyberknights4911.scouting.ScorePosition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoutReport(
    doneListener: () -> Unit
) {
    val (collectedPiece, onPieceCollectPieceSelected) = remember { mutableStateOf(GamePiece.NONE) }
    val (scorePosition, onScorePositionSelected) = remember { mutableStateOf(ScorePosition.NONE) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Auto Start")
                }
            )
        },
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = { doneListener.invoke() },
                shape = CircleShape
            ) {
                Icon(Icons.Filled.Check, "Done")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { internalPadding ->
        Column(
            modifier = Modifier
                .padding(internalPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier.padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    Text("Collection")
                    Spacer(modifier = Modifier.weight(1f))
                    Text("Count")
                }

                listOf(GamePiece.CONE, GamePiece.CUBE).forEach { piece ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (piece == collectedPiece),
                                onClick = { onPieceCollectPieceSelected(piece) }
                            )
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    ) {
                        RadioButton(
                            selected = (piece == collectedPiece),
                            onClick = { onPieceCollectPieceSelected(piece) }
                        )
                        Text(
                            text = piece.toString()
                        )
                        Spacer(
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = piece.toString()
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        enabled = (collectedPiece != GamePiece.NONE),
                        onClick = {}
                    ) {
                        Text("Collect")
                    }
                    Button(
                        modifier = Modifier.weight(1f),
                        enabled = (collectedPiece != GamePiece.NONE),
                        onClick = {}
                    ) {
                        Text("Drop")
                    }
                }
            }
            Card(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Score")
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    Spacer(
                        modifier = Modifier.weight(3f)
                    )
                    Text(GamePiece.CUBE.toString())
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                    Text(GamePiece.CONE.toString())
                }
                listOf(
                    ScorePosition.HIGH,
                    ScorePosition.MID,
                    ScorePosition.LOW
                ).forEach { position ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (position == scorePosition),
                                onClick = { onScorePositionSelected(position) }
                            )
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                    ) {
                        RadioButton(
                            selected = (position == scorePosition),
                            onClick = { onScorePositionSelected(position) }
                        )
                        Text(
                            text = position.toString()
                        )
                        Spacer(
                            modifier = Modifier.weight(2f)
                        )
                        Text(
                            // TODO put actual cone count here
                            text = position.toString()
                        )
                        Spacer(
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            // TODO put actual cube count here
                            text = position.toString()
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        enabled = (scorePosition != ScorePosition.NONE),
                        onClick = {}
                    ) {
                        Text("Score")
                    }
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Divider()
            Card(
                modifier = Modifier.padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {}
                    ) {
                        Text("Balance")
                    }
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSoutReport() {
    ScoutReport {}
}