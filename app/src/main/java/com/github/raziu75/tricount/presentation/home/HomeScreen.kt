package com.github.raziu75.tricount.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.R
import com.github.raziu75.tricount.presentation.home.composable.CountCard

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(participantCount = 4)
    }
}

@Composable
fun HomeScreen(
    participantCount: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(24.dp)) {
        CountCard(
            title = stringResource(id = R.string.home_participants_card_title),
            count = participantCount,
            icon = R.drawable.ic_person
        )
    }
}