package com.github.raziu75.tricount.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.github.raziu75.tricount.model.Screens
import com.github.raziu75.tricount.vm.GroupViewModel
import com.github.raziu75.tricount.vm.UserViewModel

@Composable
fun Home(groupVm: GroupViewModel = viewModel(), userVm: UserViewModel = viewModel() ) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screens.valueOf(backStackEntry?.destination?.route ?: Screens.HOME.name)
    Scaffold(
        topBar = {
            AppBar(
                up = { navController.navigateUp() },
                screens = currentScreen,
                canGoBack = navController.previousBackStackEntry != null,
                onClick = { groupVm.onSaveButtonClick() }
            )
        },
        content = { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screens.HOME.name,
                modifier = Modifier.padding(paddingValues)
            )
            {
                composable(Screens.HOME.name) {
                    ListGroupView(navController = navController, vm = groupVm)
                }
                composable(Screens.NEW_TRICOUNT.name) {
                    FormulairNewTricount(navController = navController, vmUser = userVm, vmGroup = groupVm)
                }
            }
        }
    )
}