package com.github.raziu75.tricount.vm

import androidx.lifecycle.ViewModel
import com.github.raziu75.tricount.model.User
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

    fun onAddUserClick(name: String){
         val newUser = User(name = name)
        groupUiState.update { it.copy(listUser = it.listUser + newUser) }
        groupUiState.value.numberUser += 1
    }

    fun onDeleteUserClick(user: User){
        groupUiState.update { currentState ->
            val updatedList = currentState.listUser.toMutableList()
            updatedList.remove(user)
            currentState.copy(listUser = updatedList)
        }
        groupUiState.value.numberUser -= 1
    }
}