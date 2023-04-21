package com.github.raziu75.tricount.ui.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.github.raziu75.tricount.model.Screens

@Composable
fun AppBar(
    screens: Screens,
    canGoBack: Boolean,
    up: () -> Unit,
    onClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = if (canGoBack) screens.title else "Tricount") },
        navigationIcon = {
            if (canGoBack) {
                IconButton(onClick = up) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        actions = {
            if (screens == Screens.NEW_TRICOUNT) {
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Save"
                    )
                }
            }
        }
    )
}
