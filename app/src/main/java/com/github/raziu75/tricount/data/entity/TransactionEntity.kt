package com.github.raziu75.tricount.data.entity

import androidx.room.*

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("transaction_id")
    val transactionId: Long = 0,

    val title: String,

    @ColumnInfo("amount_in_cents")
    val amountInCents: Int,
)