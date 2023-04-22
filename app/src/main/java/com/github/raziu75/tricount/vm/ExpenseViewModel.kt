package com.github.raziu75.tricount.vm

import com.github.raziu75.tricount.ui.uiState.ExpenseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ExpenseViewModel {
    private val ExpenseUiState = MutableStateFlow(ExpenseState())
    var uiState: StateFlow<ExpenseState> = ExpenseUiState

    fun onAmountInputChange(value: String) {
        ExpenseUiState.update { it.copy(amount = value.toDouble()) }
    }

    fun onTitleInputChange(value: String) {
        ExpenseUiState.update { it.copy(title = value) }
    }
}