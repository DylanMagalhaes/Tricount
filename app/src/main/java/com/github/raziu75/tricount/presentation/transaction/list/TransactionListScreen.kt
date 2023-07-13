package com.github.raziu75.tricount.presentation.transaction.list

import com.github.raziu75.tricount.presentation.transaction.add.state.UiState as AddTransactionUiState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.R
import com.github.raziu75.tricount.domain.model.Transaction.Participant
import com.github.raziu75.tricount.presentation.transaction.add.composable.AddTransactionBottomSheet
import com.github.raziu75.tricount.presentation.transaction.list.composable.TransactionItem
import com.github.raziu75.tricount.presentation.transaction.list.state.UiState

@Preview(showBackground = true)
@Composable
private fun TransactionListScreenPreview() {
    MaterialTheme {
        TransactionListScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = UiState(),
            addTransactionUiState = AddTransactionUiState(),
            onAddTransactionFabClick = {},
            onSheetDismiss = {},
            onSelectPayer = {},
            onTitleInputChange = {},
            onAmountInputChange = {},
            onConcernedParticipantCheckChanged = { _, _ -> },
            onPayerSelectionDropdownClick = {},
            onDismissPayerSelectionDropdownMenu = {},
            onSubmitClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionListScreen(
    uiState: UiState,
    addTransactionUiState: AddTransactionUiState,
    onAddTransactionFabClick: () -> Unit,
    onConcernedParticipantCheckChanged: (Participant, Boolean) -> Unit,
    onSelectPayer: (Participant) -> Unit,
    onAmountInputChange: (String) -> Unit,
    onTitleInputChange: (String) -> Unit,
    onSheetDismiss: () -> Unit,
    onPayerSelectionDropdownClick: () -> Unit,
    modifier: Modifier = Modifier,
    onDismissPayerSelectionDropdownMenu: () -> Unit,
    onSubmitClick: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Box(modifier = modifier) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.home_transactions_card_title),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
            )

            if (uiState.transactionList.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(id = R.string.transactions_list_transactions_empty),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(top = 32.dp),
                ) {
                    items(uiState.transactionList) { transaction ->
                        TransactionItem(transaction = transaction)
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = onAddTransactionFabClick,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .align(Alignment.BottomCenter)
        ) {
            Icon(Icons.Filled.Add, contentDescription = null)
        }

        if (uiState.addTransactionBottomSheetVisible) {
            AddTransactionBottomSheet(
                state = addTransactionUiState,
                onConcernedParticipantCheckChanged = onConcernedParticipantCheckChanged,
                onDismiss = onSheetDismiss,
                onAmountInputChange = onAmountInputChange,
                onTitleInputChange = onTitleInputChange,
                onSelectPayer = onSelectPayer,
                onPayerSelectionDropdownClick = onPayerSelectionDropdownClick,
                onDismissPayerSelectionDropdownMenu = onDismissPayerSelectionDropdownMenu,
                sheetState = sheetState,
                onSubmitClick = onSubmitClick,
            )
        }
    }
}
