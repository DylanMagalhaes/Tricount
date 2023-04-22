package com.github.raziu75.tricount.model

data class Expense(
    val title: String,
    val amount: String,
    val payerName: String,
    val participants: List<User>,
)