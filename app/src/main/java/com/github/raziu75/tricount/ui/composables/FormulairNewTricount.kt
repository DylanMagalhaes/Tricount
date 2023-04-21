package com.github.raziu75.tricount.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.raziu75.tricount.vm.GroupViewModel
import com.github.raziu75.tricount.vm.UserViewModel

@Composable
fun FormulairNewTricount(
    vmGroup: GroupViewModel = viewModel(),
    vmUser: UserViewModel = viewModel()
) {
    val groupState by vmGroup.uiState.collectAsState()
    val userState by vmUser.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = groupState.title,
            onValueChange = { newValue -> vmGroup.onTitleInputChange(newValue) },
            label = { Text(text = "Titre") },
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = groupState.description,
            onValueChange = { newValue -> vmGroup.onDescriptionInputChange(newValue) },
            label = { Text(text = "Description") }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.LightGray,
            shape = MaterialTheme.shapes.medium,
            elevation = 4.dp
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Participants: ${groupState.numberUser}"
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = userState.name,
                onValueChange = { newValue -> vmUser.onNameInputChange(newValue) },
                label = { Text(text = "Name") },

                )
            Button(onClick = { vmGroup.onAddUserClick(userState.name) }) {
                Text(text = "Add")
            }
        }
        ListUsersView(vmGroup = vmGroup)
    }
}