package com.github.raziu75.tricount.ui.uiState

import com.github.raziu75.tricount.model.Group
import com.github.raziu75.tricount.model.User

data class GroupState(
    val title: String = "",
    val description: String = "",
    var numberUser: Int = 0,
    var listUser: List<User> = listOf(),
    var listGroup: List<Group> = listOf()
)