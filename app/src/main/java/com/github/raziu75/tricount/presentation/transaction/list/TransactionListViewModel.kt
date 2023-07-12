package com.github.raziu75.tricount.presentation.transaction.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.raziu75.tricount.domain.model.Transaction
import com.github.raziu75.tricount.domain.usecases.GetAllTransactionsUseCase
import com.github.raziu75.tricount.presentation.transaction.list.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
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

    fun onAddTransactionFabClick() {
        _uiState.update {
            it.copy(addTransactionBottomSheetVisible = true)
        }
    }

    fun onDismissSheet() {
        _uiState.update {
            it.copy(addTransactionBottomSheetVisible = false)
        }
    }
}