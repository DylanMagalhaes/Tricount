package com.github.raziu75.tricount.presentation.transaction.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.raziu75.tricount.domain.model.Transaction.Participant
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

    fun onSelectPayer(value: Participant) {
        _uiState.update {
            it.copy(
                payerSelectionState = it.payerSelectionState.copy(
                    selectedPayer = value
                )
            )
        }
    }

    fun onDismissPayerSelectionDropdownMenu() {
        _uiState.update {
            it.copy(
                payerSelectionState = it.payerSelectionState.copy(
                    dropDownExpanded = false
                )
            )
        }
    }

    fun onDropDownMenuClick() {
        _uiState.update {
            it.copy(
                payerSelectionState = it.payerSelectionState.copy(
                    dropDownExpanded = true
                )
            )
        }
    }

    fun onConcernedParticipantCheckChanged(participant: Participant) {
        _uiState.update {
            val concernedParticipantsMap = it.payerSelectionState.concernedParticipants

            val updatedMap = concernedParticipantsMap.toMutableMap()
                .apply {
                    replace(participant, !requireNotNull(this[participant]))
                }

            it.copy(
                payerSelectionState = it.payerSelectionState.copy(
                    concernedParticipants = updatedMap
                )
            )
        }
    }

    private fun fetchParticipantList() {
        viewModelScope.launch {
            val participantList = fetchParticipantListUseCase()

            _uiState.update {
                it.copy(
                    payerSelectionState = it.payerSelectionState.copy(
                        availablePayerList = participantList
                    )
                )
            }
        }
    }
}
