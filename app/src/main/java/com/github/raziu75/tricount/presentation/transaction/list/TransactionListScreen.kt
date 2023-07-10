package com.github.raziu75.tricount.presentation.transaction.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.R

@Preview(showBackground = true)
@Composable
private fun TransactionListScreenPreview() {
    MaterialTheme {
        TransactionListScreen(Modifier.fillMaxSize())
    }
}

@Composable fun TransactionListScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.transactions_list_title),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
            )
        }
    }
}
