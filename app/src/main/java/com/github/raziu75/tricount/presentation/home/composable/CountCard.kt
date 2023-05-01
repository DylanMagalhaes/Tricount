package com.github.raziu75.tricount.presentation.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.presentation.common.compose.HorizontalSpacer

@Preview @Composable private fun CountCardPreview() {
    MaterialTheme {
        CountCard(
            title = "Participant",
            count = null,
            icon = Icons.Default.Person,
            onClick = {},
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class) @Composable fun CountCard(
    title: String,
    count: Int?,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 2.dp,
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(text = count?.toString() ?: "...")

            HorizontalSpacer(space = 4.dp)

            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = icon,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        }
    }
}
