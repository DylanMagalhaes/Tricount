package com.github.raziu75.tricount.presentation.transaction.list.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.R
import com.github.raziu75.tricount.domain.model.Transaction
import com.github.raziu75.tricount.presentation.common.compose.VerticalSpacer
import com.github.raziu75.tricount.presentation.participant.list.state.UiState


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddTransactionBottomSheetPreview() {
    MaterialTheme {
        val sheetState = rememberModalBottomSheetState()

        AddTransactionBottomSheet(
            sheetState = sheetState,
            onDismiss = {},
            checked = false,
            onCheckedChange = { },
            state = UiState(
                participantList = listOf(
                    Transaction.Participant(0, "Dylan"),
                    Transaction.Participant(1, "Melwin")
                )
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionBottomSheet(
    state: UiState,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = onDismiss,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            item {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = { },
                    label = { Text(stringResource(id = R.string.add_transaction_input_label_title)) }
                )

                VerticalSpacer(space = 8.dp)

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = { },
                    label = { Text(stringResource(id = R.string.add_transaction_input_label_amount)) }
                )

                VerticalSpacer(space = 8.dp)


                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = { },
                    label = { Text(stringResource(id = R.string.add_transaction_input_label_paid_by)) }
                )

                VerticalSpacer(space = 24.dp)

                Text(
                    style = MaterialTheme.typography.headlineMedium,
                    text = stringResource(id = R.string.add_transaction_participants_title)
                )

                VerticalSpacer(space = 8.dp)
            }

            items(state.participantList) { item ->
                ParticipantItem(
                    modifier = Modifier.fillMaxSize(),
                    item = item,
                    checked = checked,
                    onCheckedChange = onCheckedChange,
                )
            }
        }
    }
}

@Composable
private fun ParticipantItem(
    item: Transaction.Participant,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(text = item.name)
    }
}