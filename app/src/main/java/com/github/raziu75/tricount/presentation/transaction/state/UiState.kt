package com.github.raziu75.tricount.presentation.transaction.state

import com.github.raziu75.tricount.domain.model.Transaction

data class UiState(
    val transactionList: List<Transaction> = emptyList()
)