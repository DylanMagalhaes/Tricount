package com.github.raziu75.tricount.presentation.participant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.R
import com.github.raziu75.tricount.presentation.participant.composable.ParticipantItem
import com.github.raziu75.tricount.presentation.participant.state.UiState

@Preview(showBackground = true) @Composable private fun ParticipantListScreenPreview() {
    MaterialTheme {
        ParticipantListScreen(
            modifier = Modifier.fillMaxSize(), state = UiState(
                participantList = emptyList()
            )
        )
    }
}

@Composable fun ParticipantListScreen(
    state: UiState, modifier: Modifier = Modifier
) {
    val participantList = state.participantList
    Column(modifier = modifier.padding(24.dp)) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.participantlist_title),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
        )

        if (participantList.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = R.string.participantlist_participants_empty),
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(top = 32.dp),
            ) {
                items(participantList) { item ->
                    ParticipantItem(
                        modifier = modifier.fillMaxWidth(),
                        participant = item,
                    )
                }
            }
        }
    }
}