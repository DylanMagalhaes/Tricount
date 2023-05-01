package com.github.raziu75.tricount.presentation.participant.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.R
import com.github.raziu75.tricount.presentation.common.compose.VerticalSpacer

@Preview(showBackground = true)
@Composable
private fun AddParticipantScreenPreview() {
    MaterialTheme {
        AddParticipantScreen(
            modifier = Modifier.fillMaxWidth(),
            nameValue = "Sonny Moore",
            onNameChange = {},
            onAddButtonClick = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddParticipantScreen(
    nameValue: String,
    onNameChange: (String) -> Unit,
    onAddButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        VerticalSpacer(space = 24.dp)

        Text(
            style = MaterialTheme.typography.headlineMedium,
            text = stringResource(id = R.string.add_participant_title)
        )

        VerticalSpacer(space = 32.dp)

        OutlinedTextField(
            value = nameValue,
            onValueChange = { onNameChange(it) },
            label = { Text(text = stringResource(id = R.string.add_participant_input_label_name)) }
        )

        VerticalSpacer(space = 16.dp)

        Button(onClick = onAddButtonClick) {
            Text(text = stringResource(id = R.string.add_participant_action_add))
        }
        
        VerticalSpacer(space = 24.dp)
    }
}