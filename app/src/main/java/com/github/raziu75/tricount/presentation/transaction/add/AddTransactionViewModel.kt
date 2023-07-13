package com.github.raziu75.tricount.presentation.transaction.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.raziu75.tricount.domain.model.Transaction
import com.github.raziu75.tricount.domain.model.Transaction.Participant
import com.github.raziu75.tricount.domain.usecases.CreateTransactionUseCase
import com.github.raziu75.tricount.domain.usecases.FetchParticipantListUseCase
import com.github.raziu75.tricount.presentation.transaction.add.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AddTransactionViewModel
@Inject constructor(
    private val fetchParticipantListUseCase: FetchParticipantListUseCase,
    private val createTransactionUseCase: CreateTransactionUseCase,

    ) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _navigationEvents = Channel<NavigationEvent>()
    val navigationEvents = _navigationEvents.receiveAsFlow()

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

    fun onSelectPayer(payer: Participant) {
        _uiState.update {
            val concernedParticipantsMap = it.payerSelectionState.concernedParticipants
            val updatedMap = concernedParticipantsMap.toMutableMap()
                .apply {
                    replace(payer, true)
                }
            it.copy(
                payerSelectionState = it.payerSelectionState.copy(
                    selectedPayer = payer,
                    dropDownExpanded = false,
                    concernedParticipants = updatedMap
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

    fun onSubmitClick() {
        val uiState = uiState.value

        val participantsChecked = uiState.payerSelectionState.concernedParticipants.filterValues { it }
        val concernedParticipant = participantsChecked.keys.toList()

        viewModelScope.launch {
            createTransactionUseCase(
                Transaction(
                    amountInCents = uiState.amount.toInt(),
                    title = uiState.title,
                    payer = uiState.payerSelectionState.selectedPayer!!,
                    concernedParticipants = concernedParticipant,
                )
            )

            _navigationEvents.send(NavigationEvent.Dismiss)
        }
    }

    private fun fetchParticipantList() {
        viewModelScope.launch {
            val participantList = fetchParticipantListUseCase()

            _uiState.update {
                it.copy(
                    payerSelectionState = it.payerSelectionState.copy(
                        availablePayerList = participantList,
                        concernedParticipants = participantList.associateWith { false }
                    )
                )
            }
        }
    }

    sealed class NavigationEvent {
        object Dismiss : NavigationEvent()
    }
}
