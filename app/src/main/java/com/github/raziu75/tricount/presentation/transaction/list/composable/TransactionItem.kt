package com.github.raziu75.tricount.presentation.transaction.list.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.R
import com.github.raziu75.tricount.domain.model.Transaction
import com.github.raziu75.tricount.presentation.common.compose.VerticalSpacer

@Preview(showBackground = true) @Composable private fun TransactionItemPreview() {
    MaterialTheme {
        TransactionItem(
            modifier = Modifier.fillMaxWidth(),
            transaction = Transaction(
                id = 1,
                amountInCents = 5465,
                title = "La Zoumalikatou t'as mal au crâne",
                payer = Transaction.Participant(0, "Melwin"),
                concernedParticipants = listOf(),
            ),
        )
    }
}

@Composable fun TransactionItem(
    transaction: Transaction,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shadowElevation = 2.dp,
        shape = MaterialTheme.shapes.small,
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = transaction.title,
                    style = MaterialTheme.typography.bodyMedium,
                )

                VerticalSpacer(4.dp)

                Text(
                    text = stringResource(
                        R.string.transaction_list_item_paid_by_label,
                        transaction.payer.name,
                    ),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            VerticalSpacer(8.dp)

            Text(
                text = stringResource(
                    R.string.transaction_list_item_price_label,
                    transaction.amountInCents.div(100),
                ),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}