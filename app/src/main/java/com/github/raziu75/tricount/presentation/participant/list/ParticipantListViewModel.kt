package com.github.raziu75.tricount.presentation.participant.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.raziu75.tricount.data.TricountRepository
import com.github.raziu75.tricount.domain.model.Transaction.Participant
import com.github.raziu75.tricount.presentation.participant.list.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel class ParticipantListViewModel
@Inject constructor(
    private val repository: TricountRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchParticipantList()
    }

    fun onNameInputChange(value: String) {
        val isInputValid = value.isNotBlank()

        _uiState.update {
            it.copy(
                addParticipantSubmitButtonEnabled = isInputValid,
                nameValue = value
            )
        }
    }

    fun onAddParticipantSubmitClick() {
        if (uiState.value.nameValue.isBlank()) return

        viewModelScope.launch {
            repository.createParticipant(uiState.value.nameValue)
        }

        _uiState.update {
            it.copy(nameValue = "")
        }

        fetchParticipantList()
    }

    fun onDeleteParticipantClick(participant: Participant) {
        viewModelScope.launch {
            repository.deleteParticipant(participant)

            _uiState.update {
                it.copy(
                    participantList =
                    it.participantList
                        .toMutableList()
                        .apply { remove(participant) }
                )
            }
        }
    }

    fun onAddParticipantFabClick() {
        _uiState.update {
            it.copy(addParticipantBottomSheetVisible = true)
        }
    }

    fun onAddParticipantDismiss() {
        _uiState.update {
            it.copy(addParticipantBottomSheetVisible = false)
        }
    }

    private fun fetchParticipantList() {
        viewModelScope.launch {
            val participantList = repository.getParticipants()

            _uiState.update {
                it.copy(participantList = participantList)
            }
        }
    }
}
