package com.cyberknights4911.scouting.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyberknights4911.scouting.Balance

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalanceSelector(
    onSelect: (Balance) -> Unit
) {
    val balanceOptions = listOf(
        Balance.PARKED,
        Balance.DOCKED,
        Balance.ENGAGED
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(Balance.NONE) }

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
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            balanceOptions.forEach { balance ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (balance == selectedOption),
                            onClick = {
                                onOptionSelected(balance)
                            }
                        )
                        .padding(horizontal = 16.dp)
                ) {
                    RadioButton(
                        selected = (balance == selectedOption),
                        onClick = { onOptionSelected(balance) }
                    )
                    Text(
                        text = balance.toString(),
                        modifier = Modifier.padding(all = 16.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    enabled = (selectedOption != Balance.NONE),
                    onClick = {
                        onSelect.invoke(Balance.NONE)
                    }
                ) {
                    Text (
                        text = "Cancel"
                    )
                }
                Button(
                    enabled = (selectedOption != Balance.NONE),
                    onClick = {
                        onSelect.invoke(selectedOption)
                    }
                ) {
                    Text (
                        text = "Done"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewBalanceSelector() {
    BalanceSelector(onSelect = {})
}


