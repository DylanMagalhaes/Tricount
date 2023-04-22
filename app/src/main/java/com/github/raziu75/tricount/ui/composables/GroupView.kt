package com.github.raziu75.tricount.ui.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.raziu75.tricount.model.Group

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupView(
    group: Group,
    onDelete: () -> Unit,
    onClick: (Group) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(100.dp),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.medium,
        onClick = { onClick(group) }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(start = 24.dp, top = 8.dp, bottom = 8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = group.title,
                    style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colors.primaryVariant
                )
                Text(
                    text = group.description,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 24.dp)
            ) {
                Text(
                    text = group.numberUser.toString(),
                    style = MaterialTheme.typography.subtitle1
                )
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.padding(start = 8.dp)
                )

                val scale by remember { mutableStateOf(1f) }
                val iconColor by animateColorAsState(
                    targetValue = if (scale == 1f) {
                        MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                    } else {
                        MaterialTheme.colors.secondary
                    }
                )

                IconButton(
                    onClick = { onDelete() },
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .scale(scale)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = iconColor
                    )
                }
            }
        }
    }
}
