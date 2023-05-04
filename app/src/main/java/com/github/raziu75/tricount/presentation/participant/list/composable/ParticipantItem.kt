package com.github.raziu75.tricount.presentation.participant.list.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.domain.model.Transaction.Participant

@Preview(showBackground = true)
@Composable
private fun ParticipantItemPreview() {
    MaterialTheme {
        ParticipantItem(
            modifier = Modifier.fillMaxWidth(),
            participant = Participant(id = 0, name = "Dylan"),
            onDeleteClick = {},
        )
    }
}

@Composable
fun ParticipantItem(
    participant: Participant,
    onDeleteClick: (Participant) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shadowElevation = 2.dp,
        shape = MaterialTheme.shapes.small,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = participant.name,
                style = MaterialTheme.typography.bodyMedium,
            )

            IconButton(onClick = { onDeleteClick(participant) }) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.primary,
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                )
            }
        }
    }
}