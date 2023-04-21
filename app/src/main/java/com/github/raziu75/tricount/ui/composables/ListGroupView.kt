package com.github.raziu75.tricount.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.raziu75.tricount.model.Screens
import com.github.raziu75.tricount.vm.GroupViewModel

@Composable
fun ListGroupView(vm: GroupViewModel, navController: NavController) {
    val groupState by vm.uiState.collectAsState()
    val groupList = groupState.listGroup

    Column(modifier = Modifier.fillMaxSize()) {
        if (groupList.isEmpty()) {
            Text(text = "Pas encore de group crée")
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(groupList) { group ->
                    GroupView(group = group, onDelete = { })
                }
            }
        }

        Box(
            modifier = Modifier
                .padding(16.dp)

                .weight(1f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(onClick = { navController.navigate(Screens.NEW_TRICOUNT.name) }) {
                Text(text = "Ajouter un groupe")
            }
        }
    }
}
