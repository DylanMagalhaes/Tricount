package com.github.raziu75.tricount.presentation.transaction.list.state

import com.github.raziu75.tricount.domain.model.Transaction

data class UiState(
    val transactionList: List<Transaction> = emptyList(),
    val addTransactionBottomSheetVisible: Boolean = false
)