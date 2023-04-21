package com.github.raziu75.tricount.vm

import androidx.lifecycle.ViewModel
import com.github.raziu75.tricount.model.Group
import com.github.raziu75.tricount.model.User
import com.github.raziu75.tricount.ui.uiState.GroupState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GroupViewModel : ViewModel() {

    private val groupUiState = MutableStateFlow(GroupState())
    var uiState: StateFlow<GroupState> = groupUiState


    fun onTitleInputChange(value: String) {
        groupUiState.update { it.copy(title = value) }
    }

    fun onDescriptionInputChange(value: String) {
        groupUiState.update { it.copy(description = value) }
    }

    fun onAddUserClick(name: String) {
        val newUser = User(name = name)
        if (name != "") {
            groupUiState.update { it.copy(listUser = it.listUser + newUser) }
            groupUiState.value.numberUser += 1
        }
    }

    fun onSaveButtonClick() {
        val newGroup = Group(
            title = groupUiState.value.title,
            description = groupUiState.value.description,
            numberUser = groupUiState.value.numberUser
        )
        groupUiState.update {
            it.copy(listGroup = it.listGroup + newGroup)
        }
        println(groupUiState.value)
        println(groupUiState.value.listGroup)
        println(groupUiState.value.description)
        println(groupUiState.value.title)
        println(groupUiState.value.numberUser)
    }

    fun onDeleteGroupClick(group: Group){
        groupUiState.update { currentState ->
            val updatedList = currentState.listGroup.toMutableList()
            updatedList.remove(group)
            currentState.copy(listGroup = updatedList)
        }
    }


    fun onDeleteUserClick(user: User) {
        groupUiState.update { currentState ->
            val updatedList = currentState.listUser.toMutableList()
            updatedList.remove(user)
            currentState.copy(listUser = updatedList)
        }
        groupUiState.value.numberUser -= 1
    }
}