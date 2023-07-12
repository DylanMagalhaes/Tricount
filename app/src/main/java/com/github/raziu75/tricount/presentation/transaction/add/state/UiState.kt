package com.github.raziu75.tricount.presentation.transaction.add.state

data class UiState(
    val title: String = "",
    val amount: String = "",
    val payerSelectionState: PayerSelectionState = PayerSelectionState()
)
