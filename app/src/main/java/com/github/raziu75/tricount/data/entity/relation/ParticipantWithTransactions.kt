package com.github.raziu75.tricount.data.entity.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.raziu75.tricount.data.entity.TransactionEntity
import com.github.raziu75.tricount.data.entity.ParticipantEntity

data class ParticipantWithTransactions(
    @Embedded val participants: ParticipantEntity,

    @Relation(
        parentColumn = "participant_id",
        entityColumn = "transaction_id",
        associateBy = Junction(TransactionParticipantCrossRef::class)
    )
    val transactions: List<TransactionEntity>,
)
