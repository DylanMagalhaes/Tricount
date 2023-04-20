package com.github.raziu75.tricount.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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

@Composable
fun FormulairNewTricount(vm: GroupViewModel = viewModel()) {
    val groupState by vm.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = groupState.title,
            onValueChange = {newValue -> vm.onTitleInputChange(newValue)},
            label = { Text(text = "Titre") },
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = groupState.description,
            onValueChange = {newValue -> vm.onDescriptionInputChange(newValue)},
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
                text = "Participants: 0")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Name") },

            )
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Add")
            }
        }
        
        LazyColumn(){

        }
    }
}