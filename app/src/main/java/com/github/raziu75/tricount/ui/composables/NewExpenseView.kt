package com.github.raziu75.tricount.ui.composables

import UserDropdownMenu
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
import com.github.raziu75.tricount.vm.ExpenseViewModel
import com.github.raziu75.tricount.vm.GroupViewModel
import com.github.raziu75.tricount.vm.UserViewModel

@Composable
fun NewExpenseView(navController: NavController, vmGroup: GroupViewModel, vmExpense: ExpenseViewModel) {
    val groupState by vmGroup.uiState.collectAsState()
    val expenseState by vmExpense.uiState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Nouvelle depense",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = expenseState.title,
            onValueChange = { newValue -> vmExpense.onTitleInputChange(newValue) },
            label = { Text(text = "Titre") },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                // modifier = Modifier.fillMaxWidth(),
                value = expenseState.amount,
                onValueChange = { newValue -> vmExpense.onAmountInputChange(newValue) },
                label = { Text(text = "Montant") }
            )
            Text(text = "EUR (€)")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.primaryVariant,
            shape = MaterialTheme.shapes.medium,
            elevation = 4.dp
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Payé par :",
                    color = Color.White
                )
                UserDropdownMenu(vmGroup)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(startIndent = 8.dp, thickness = 1.dp, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        // Ajouter liste des membre avec une case cochable a coter de chaque
    }
}

