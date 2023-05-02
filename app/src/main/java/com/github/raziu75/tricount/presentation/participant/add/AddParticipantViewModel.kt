package com.github.raziu75.tricount.presentation.participant.add

import androidx.lifecycle.ViewModel
import com.github.raziu75.tricount.data.TricountRepository
import com.github.raziu75.tricount.presentation.participant.add.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class AddParticipantViewModel
@Inject constructor(
    private val repository: TricountRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun onNameInputChange(value: String) {
        _uiState.update {
            it.copy(nameValue = value)
        }
    }

}