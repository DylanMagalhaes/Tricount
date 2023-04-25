package com.github.raziu75.tricount.data.entity.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.raziu75.tricount.data.entity.TransactionEntity
import com.github.raziu75.tricount.data.entity.ParticipantEntity
import com.github.raziu75.tricount.data.entity.TripEntity

data class TripWithTransactions(
    @Embedded val trip: TripEntity,

    @Relation(
        parentColumn = "trip_id",
        entityColumn = "transaction_id",
    )
    val transactions: List<TransactionEntity>,
)
