package com.github.raziu75.tricount.presentation.transaction.add

import androidx.lifecycle.ViewModel
import com.github.raziu75.tricount.domain.model.Transaction
import com.github.raziu75.tricount.presentation.transaction.add.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddTransactionViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun onTitleInputChange(value: String) {
        _uiState.update {
            it.copy(
                title = value
            )
        }
    }

    fun onAmountInputChange(value: String) {
        _uiState.update {
            it.copy(
                amount = value.toLong()
            )
        }
    }

    fun onSelectPayer(value: Transaction.Participant) {
        _uiState.update {
            it.copy(
                payer = value
            )
        }
    }
}