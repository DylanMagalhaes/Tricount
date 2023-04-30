package com.github.raziu75.tricount.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.raziu75.tricount.data.TricountRepository
import com.github.raziu75.tricount.presentation.home.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: TricountRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val participants = repository.getParticipants()

            _uiState.update {
                it.copy(participantCount = participants.size)
            }
        }
    }
}