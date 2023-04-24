package com.github.raziu75.tricount.data.entity

data class Expense(
    val id: String,
    val title: String,
    val amountInCents: Int,
    val payer: User,
    val participants: List<User>
)
