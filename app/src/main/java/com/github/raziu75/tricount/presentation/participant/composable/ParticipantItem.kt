package com.github.raziu75.tricount.presentation.participant.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.domain.model.Transaction

@Preview(showBackground = true)
@Composable
private fun ParticipantItemPreview() {
    MaterialTheme {
        ParticipantItem(
            modifier = Modifier.fillMaxWidth(),
            participant = Transaction.Participant(id = 0, name = "Dylan")
        )
    }
}

@Composable
fun ParticipantItem(
    participant: Transaction.Participant,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shadowElevation = 2.dp,
        shape = MaterialTheme.shapes.small,
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = participant.name,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}