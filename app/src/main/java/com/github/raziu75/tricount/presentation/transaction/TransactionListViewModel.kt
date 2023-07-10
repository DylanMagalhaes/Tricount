package com.github.raziu75.tricount.presentation.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.raziu75.tricount.domain.usecases.GetAllTransactionsUseCase
import com.github.raziu75.tricount.presentation.transaction.state.UiState
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TransactionListViewModel @Inject constructor(
    private val getAllTransactionsUseCase: GetAllTransactionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchTransactionList()
    }

    private fun fetchTransactionList() {
        viewModelScope.launch {
            val transactionList = getAllTransactionsUseCase()

            _uiState.update {
                it.copy(transactionList = transactionList)
            }
        }
    }
}