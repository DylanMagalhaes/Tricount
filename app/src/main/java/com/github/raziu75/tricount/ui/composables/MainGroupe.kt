package com.github.raziu75.tricount.ui.composables

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.github.raziu75.tricount.model.Screens
import com.github.raziu75.tricount.vm.GroupViewModel
import com.github.raziu75.tricount.vm.UserViewModel

@Composable
fun MainGroup(
    vmGroup: GroupViewModel,
    vmUser: UserViewModel,
    navController: NavController
) {
    Button(onClick = { navController.navigate(Screens.NEW_EXPENSE.name) }) {
        Text(text = "Ajouter dépense")
    }
}