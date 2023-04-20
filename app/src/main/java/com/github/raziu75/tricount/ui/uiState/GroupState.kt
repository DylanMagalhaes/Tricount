package com.github.raziu75.tricount.ui.uiState

import com.github.raziu75.tricount.model.User

data class GroupState(
    val title: String = "",
    val description: String = "",
    val numberUser: Int = 0,
    var listUser: List<User> = listOf()
)