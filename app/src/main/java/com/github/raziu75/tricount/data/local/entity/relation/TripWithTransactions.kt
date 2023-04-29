package com.github.raziu75.tricount.data.local.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.github.raziu75.tricount.data.local.entity.TransactionEntity
import com.github.raziu75.tricount.data.local.entity.TripEntity

data class TripWithTransactions(
    @Embedded val trip: TripEntity,

    @Relation(
        parentColumn = "trip_id",
        entityColumn = "transaction_id",
    )
    val transactions: List<TransactionEntity>,
)
