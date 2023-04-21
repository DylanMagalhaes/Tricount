package com.github.raziu75.tricount.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.github.raziu75.tricount.vm.GroupViewModel

@Composable
fun ListUsersView(vmGroup: GroupViewModel) {
    val groupState by vmGroup.uiState.collectAsState()
    val userList = groupState.listUser
    if (userList.isEmpty()) {
        Text(text = "Pas encore de participant")
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(userList) { user ->
                UserView(user = user, onDelete = { vmGroup.onDeleteUserClick(user) })
            }
        }
    }
}