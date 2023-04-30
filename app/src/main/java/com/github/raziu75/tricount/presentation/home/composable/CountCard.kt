package com.github.raziu75.tricount.presentation.home.composable

import androidx.annotation.DrawableRes
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.raziu75.tricount.R

@Preview
@Composable
private fun CountCardPreview() {
    MaterialTheme {
        CountCard(
            title = "Participant",
            count = 4,
            icon = R.drawable.ic_person,
        )
    }
}

@Composable
fun CountCard(
    title: String,
    count: Int,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
) {
    TODO()
}