package com.cyberknights4911.scouting.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.cyberknights4911.scouting.Balance
import com.cyberknights4911.scouting.GamePiece
import com.cyberknights4911.scouting.R
import com.cyberknights4911.scouting.autostart.AutoStartPosition

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun AutoStartLayout(
    doneListener: (AutoStartPosition) -> Unit
) {
    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(AutoStartPosition.NONE)
    }

    val (selectedPiece, onPieceSelected) = remember { mutableStateOf(GamePiece.NONE) }
    
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
                onClick = { doneListener.invoke(selectedOption) },
                shape = CircleShape
            ) {
                Icon(Icons.Filled.Check, "Done")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { internalPadding ->

        ConstraintLayout(
            modifier = Modifier.padding(internalPadding)
        ) {
            val (
                field,
                buttonOne,
                buttonTwo,
                buttonThree,
                buttonFour,
                buttonNone,
                pieceSelector
            ) = createRefs()

            GlideImage(
                model = R.raw.start_field,
                contentDescription = "",
                modifier = Modifier.aspectRatio(1696f / 1894f).constrainAs(field) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                loading = placeholder(R.drawable.ic_launcher_background)
            ) { requestBuilder ->
                requestBuilder.load(R.raw.start_field)
            }
            Button(
                onClick = { onOptionSelected.invoke(AutoStartPosition.ONE) },
                modifier = Modifier.width(120.dp)
                    .height(120.dp)
                    .constrainAs(buttonOne) {
                    top.linkTo(field.top)
                    start.linkTo(field.start)
                    linkTo(start = parent.start, end = field.end, bias = 0.1f)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = when (selectedOption) {
                        AutoStartPosition.ONE ->  MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.tertiary
                    }
                )
            ) {
                Text(AutoStartPosition.ONE.toString())
            }
            Button(
                onClick = { onOptionSelected.invoke(AutoStartPosition.TWO) },
                modifier = Modifier.width(120.dp).height(120.dp).constrainAs(buttonTwo) {
                    top.linkTo(field.top)
                    bottom.linkTo(buttonThree.top)
                    start.linkTo(field.start)
                    linkTo(start = field.start, end = field.end, bias = 0.7f)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = when (selectedOption) {
                        AutoStartPosition.TWO ->  MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.tertiary
                    }
                )
            ) {
                Text(AutoStartPosition.TWO.toString())
            }
            Button(
                onClick = { onOptionSelected.invoke(AutoStartPosition.THREE) },
                modifier = Modifier.width(120.dp).height(120.dp).constrainAs(buttonThree) {
                    top.linkTo(buttonTwo.bottom)
                    bottom.linkTo(buttonFour.top)
                    start.linkTo(field.start)
                    linkTo(start = field.start, end = field.end, bias = 0.7f)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = when (selectedOption) {
                        AutoStartPosition.THREE ->  MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.tertiary
                    }
                )
            ) {
                Text(AutoStartPosition.THREE.toString())
            }
            Button(
                onClick = { onOptionSelected.invoke(AutoStartPosition.FOUR) },
                modifier = Modifier.width(120.dp).height(120.dp).constrainAs(buttonFour) {
                    top.linkTo(buttonThree.bottom)
                    bottom.linkTo(field.bottom)
                    start.linkTo(parent.start)
                    linkTo(start = field.start, end = field.end, bias = 0.7f)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = when (selectedOption) {
                        AutoStartPosition.FOUR ->  MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.tertiary
                    }
                )
            ) {
                Text(AutoStartPosition.FOUR.toString())
            }
            Button(
                onClick = { onOptionSelected.invoke(AutoStartPosition.NONE) },
                modifier = Modifier.padding(horizontal = 16.dp).constrainAs(buttonNone) {
                    top.linkTo(buttonFour.bottom)
                    start.linkTo(parent.start)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = when (selectedOption) {
                        AutoStartPosition.NONE ->  MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.tertiary
                    }
                )
            ) {
                Text("No Show")
            }
            Column(
                modifier = Modifier.padding(16.dp).constrainAs(pieceSelector) {
                    top.linkTo(buttonNone.bottom)
                    start.linkTo(parent.start)
                },
            ) {
                Divider(modifier = Modifier.fillMaxWidth().width(1.dp))
                Text("Starting Game Piece")
                GamePiece.values().forEach { piece ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (piece == selectedPiece),
                                onClick = { onPieceSelected(piece) }
                            )
                            .padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = (piece == selectedPiece),
                            onClick = { onPieceSelected(piece) }
                        )
                        Text(
                            text = piece.toString()
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAutoStartLayout() {
    AutoStartLayout {}
}