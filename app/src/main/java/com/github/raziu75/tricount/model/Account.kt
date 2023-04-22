package com.github.raziu75.tricount.model

data class Account(
    val listExpenses: List<Expense> = listOf(),
    val currentBalance: Expense
)
