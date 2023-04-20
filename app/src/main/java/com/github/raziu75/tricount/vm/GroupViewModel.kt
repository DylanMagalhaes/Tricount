package com.github.raziu75.tricount.vm

import androidx.lifecycle.ViewModel
import com.github.raziu75.tricount.ui.uiState.GroupState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GroupViewModel: ViewModel() {

    private val groupUiState = MutableStateFlow(GroupState())
    var uiState: StateFlow<GroupState> = groupUiState

    fun onTitleInputChange(value: String){
        groupUiState.update { it.copy(title = value) }
    }
    fun onDescriptionInputChange(value: String){
        groupUiState.update { it.copy(description = value) }
    }
}