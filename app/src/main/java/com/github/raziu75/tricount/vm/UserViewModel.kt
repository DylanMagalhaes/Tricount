package com.github.raziu75.tricount.vm

import androidx.lifecycle.ViewModel
import com.github.raziu75.tricount.ui.uiState.GroupState
import com.github.raziu75.tricount.ui.uiState.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UserViewModel: ViewModel() {
    private val userUiState = MutableStateFlow(UserState())
    var uiState: StateFlow<UserState> = userUiState

    fun onNameInputChange(value: String){
        userUiState.update { it.copy(name = value) }
    }
}