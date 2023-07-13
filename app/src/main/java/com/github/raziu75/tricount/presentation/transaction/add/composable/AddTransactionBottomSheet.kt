package com.github.raziu75.tricount.presentation.transaction.add.composable

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.R
import com.github.raziu75.tricount.domain.model.Transaction.Participant
import com.github.raziu75.tricount.presentation.common.compose.VerticalSpacer
import com.github.raziu75.tricount.presentation.transaction.add.state.UiState


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddTransactionBottomSheetPreview() {
    MaterialTheme {
        val sheetState = rememberModalBottomSheetState()

        AddTransactionBottomSheet(
            onAmountInputChange = {},
            onTitleInputChange = {},
            sheetState = sheetState,
            onDismiss = {},
            onConcernedParticipantCheckChanged = { _, _ -> },
            state = UiState(),
            onSelectPayer = {},
            onPayerSelectionDropdownClick = {},
            onDismissPayerSelectionDropdownMenu = {},
            onSubmitClick = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionBottomSheet(
    state: UiState,
    onConcernedParticipantCheckChanged: (Participant, Boolean) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onAmountInputChange: (String) -> Unit,
    onTitleInputChange: (String) -> Unit,
    onSelectPayer: (Participant) -> Unit,
    onPayerSelectionDropdownClick: () -> Unit,
    onDismissPayerSelectionDropdownMenu: () -> Unit,
    onSubmitClick: () -> Unit
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
                    value = state.title,
                    singleLine = true,
                    onValueChange = onTitleInputChange,
                    label = { Text(stringResource(id = R.string.add_transaction_input_label_title)) }
                )

                VerticalSpacer(space = 8.dp)

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.amount,
                    singleLine = true,
                    onValueChange = onAmountInputChange,
                    label = { Text(stringResource(id = R.string.add_transaction_input_label_amount)) }
                )

                VerticalSpacer(space = 8.dp)

                Box {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.payerSelectionState.selectedPayer?.name ?: "",
                        interactionSource = remember { MutableInteractionSource() }
                            .also { interactionSource ->
                                LaunchedEffect(interactionSource) {
                                    interactionSource.interactions.collect {
                                        if (it is PressInteraction.Release) {
                                            onPayerSelectionDropdownClick()
                                        }
                                    }
                                }
                            },
                        onValueChange = {},
                        readOnly = true,
                        singleLine = true,
                        label = { Text(stringResource(id = R.string.add_transaction_input_label_paid_by)) },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null,
                            )
                        }
                    )

                    DropdownMenu(
                        expanded = state.payerSelectionState.dropDownExpanded,
                        onDismissRequest = onDismissPayerSelectionDropdownMenu,
                    ) {
                        state.payerSelectionState.availablePayerList.forEach { participant ->
                            DropdownMenuItem(
                                onClick = { onSelectPayer(participant) },
                                text = { Text(text = participant.name) }
                            )
                        }
                    }
                }

                VerticalSpacer(space = 24.dp)

                Text(
                    style = MaterialTheme.typography.headlineMedium,
                    text = stringResource(id = R.string.add_transaction_participants_title)
                )

                VerticalSpacer(space = 8.dp)
            }

            state.payerSelectionState.concernedParticipants.forEach { (participant, concerned) ->
                item {
                    ParticipantItem(
                        modifier = Modifier.fillMaxSize(),
                        item = participant,
                        checked = concerned,
                        onCheckedChange = {
                            onConcernedParticipantCheckChanged(
                                participant,
                                concerned
                            )
                        },
                    )
                }
            }

            item {
                VerticalSpacer(space = 24.dp)

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onSubmitClick,
                ) {
                    Text(text = stringResource(id = R.string.add_participant_action_add))
                }

                VerticalSpacer(space = 24.dp)
            }
        }
    }
}

@Composable
private fun ParticipantItem(
    item: Participant,
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
