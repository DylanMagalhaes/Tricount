package com.github.raziu75.tricount.presentation.transaction.add.state

import com.github.raziu75.tricount.domain.model.Transaction

data class UiState(
    val participantList: List<Transaction.Participant> = emptyList(),
    val title: String = "",
    val amount: String = "",
    val payer: Transaction.Participant? = null,
)
