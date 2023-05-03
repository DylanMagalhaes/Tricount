package com.github.raziu75.tricount.presentation.participant.list.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.R
import com.github.raziu75.tricount.presentation.common.compose.VerticalSpacer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddParticipantBottomSheet(
    nameValue: String,
    submitButtonEnabled: Boolean,
    onNameChange: (String) -> Unit,
    onAddButtonClick: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                style = MaterialTheme.typography.headlineMedium,
                text = stringResource(id = R.string.add_participant_title)
            )

            VerticalSpacer(space = 32.dp)

            OutlinedTextField(value = nameValue,
                onValueChange = { onNameChange(it) },
                label = { Text(text = stringResource(id = R.string.add_participant_input_label_name)) })

            VerticalSpacer(space = 16.dp)

            Button(
                onClick = onAddButtonClick,
                enabled = submitButtonEnabled
            ) {
                Text(text = stringResource(id = R.string.add_participant_action_add))
            }

            VerticalSpacer(space = 24.dp)
        }
    }
}
