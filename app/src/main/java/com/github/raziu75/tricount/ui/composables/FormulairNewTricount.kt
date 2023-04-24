package com.github.raziu75.tricount.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.raziu75.tricount.vm.GroupViewModel
import com.github.raziu75.tricount.vm.UserViewModel

@Composable
fun FormulairNewTricount(
    vmGroup: GroupViewModel,
    vmUser: UserViewModel,
    navController: NavController
) {
    val groupState by vmGroup.uiState.collectAsState()
    val userState by vmUser.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Nouveau Tricount",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = groupState.title,
            onValueChange = { newValue -> vmGroup.onTitleInputChange(newValue) },
            label = { Text(text = "Titre") },
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = groupState.description,
            onValueChange = { newValue -> vmGroup.onDescriptionInputChange(newValue) },
            label = { Text(text = "Description") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.primaryVariant,
            shape = MaterialTheme.shapes.medium,
            elevation = 4.dp
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Participants: ${groupState.numberUser}",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = userState.name,
                onValueChange = { newValue -> vmUser.onNameInputChange(newValue) },
                label = { Text(text = "Nom") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { vmGroup.onAddUserClick(userState.name) }) {
                Text(text = "Ajouter")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ListUsersView(
            state = groupState,
            onDeleteUserClick = { vmGroup.onDeleteUserClick(it) }
        )
    }
}
