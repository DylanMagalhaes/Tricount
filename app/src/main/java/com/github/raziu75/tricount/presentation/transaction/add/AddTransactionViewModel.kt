package com.github.raziu75.tricount.presentation.transaction.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.raziu75.tricount.domain.model.Transaction
import com.github.raziu75.tricount.domain.usecases.FetchParticipantListUseCase
import com.github.raziu75.tricount.presentation.transaction.add.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AddTransactionViewModel
@Inject constructor(
    private val fetchParticipantListUseCase: FetchParticipantListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchParticipantList()
    }

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
                amount = value
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

    private fun fetchParticipantList() {
        viewModelScope.launch {
            val participantList = fetchParticipantListUseCase()

            _uiState.update {
                it.copy(participantList = participantList)
            }
        }
    }

    fun onDropDownMenuClick() {
        _uiState.update {
            it.copy(payerDropdownMenuExpanded = true)
        }
    }
}