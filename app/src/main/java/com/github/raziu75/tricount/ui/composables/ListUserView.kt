package com.github.raziu75.tricount.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.raziu75.tricount.model.Account
import com.github.raziu75.tricount.model.Expense
import com.github.raziu75.tricount.model.User
import com.github.raziu75.tricount.ui.uiState.GroupState

@Preview(showBackground = true)
@Composable
private fun UserListViewPreview() {
    MaterialTheme {
        ListUsersView(
            state = GroupState(
                title = "Toto",
                description = "Tata",
                numberUser = 5,
                listUser = listOf(
                    User(
                        name = "Dylan",
                        account = Account(
                            listExpenses = emptyList(),
                            currentBalance = Expense(
                                title = "une dépense",
                                amount = "13€",
                                payerName = "Melwin",
                                participants = emptyList()
                            )
                        )
                    )
                ),
                listGroup = emptyList(),
            ),
            onDeleteUserClick = {},
        )
    }
}


@Composable
fun ListUsersView(
    state: GroupState,
    onDeleteUserClick: (User) -> Unit,
) {
    if (state.listUser.isEmpty()) {
        Text(text = "Pas encore de participant")
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.listUser) { user ->
                UserView(user = user, onDelete = { onDeleteUserClick(user) })
            }
        }
    }
}