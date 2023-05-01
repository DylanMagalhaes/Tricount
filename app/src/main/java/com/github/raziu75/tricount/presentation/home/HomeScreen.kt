package com.github.raziu75.tricount.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.R
import com.github.raziu75.tricount.presentation.home.composable.CountCard
import com.github.raziu75.tricount.presentation.home.state.UiState

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(state = UiState(participantCount = 4))
    }
}

@Composable
fun HomeScreen(
    state: UiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
    ) {
        CountCard(
            title = stringResource(id = R.string.home_participants_card_title),
            count = state.participantCount,
            icon = Icons.Default.Person,
        )
    }
}