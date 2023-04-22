package com.github.raziu75.tricount.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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

    Box(modifier = Modifier.fillMaxSize()) {
        if (groupList.isEmpty()) {
            Text(text = "Pas encore de group crée", modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(groupList) { group ->
                    GroupView(
                        group = group,
                        onDelete = { vm.onDeleteGroupClick(group) },
                        onClick = {
                            navController.navigate(
                                Screens.MAIN_GROUP.name
                            )
                        })
                }
            }
        }
        FloatingActionButton(
            onClick = { navController.navigate(Screens.NEW_TRICOUNT.name) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Ajouter un groupe")
        }
    }
}
