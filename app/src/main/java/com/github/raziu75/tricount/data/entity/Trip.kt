package com.github.raziu75.tricount.data.entity

data class Trip(
    val id: String,
    val title: String,
    val description: String,
    val participants: List<User>,
    val expenses: List<Expense>
)
