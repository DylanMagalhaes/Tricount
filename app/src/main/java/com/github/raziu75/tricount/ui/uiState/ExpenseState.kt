package com.github.raziu75.tricount.ui.uiState

import com.github.raziu75.tricount.model.User

data class ExpenseState(
    val title: String = "",
    val amount: String = "",
    val payerName: String = "",
    val participants: List<User> = listOf(),
)