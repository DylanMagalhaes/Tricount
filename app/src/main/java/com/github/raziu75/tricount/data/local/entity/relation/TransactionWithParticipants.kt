package com.github.raziu75.tricount.data.local.entity.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.github.raziu75.tricount.data.local.entity.TransactionEntity
import com.github.raziu75.tricount.data.local.entity.ParticipantEntity

data class TransactionWithParticipants(
    @Embedded val transaction: TransactionEntity,

    @Relation(
        parentColumn = "transaction_id",
        entityColumn = "participant_id",
        associateBy = Junction(TransactionParticipantCrossRef::class)
    )
    val participants: List<ParticipantEntity>,

    @Relation(
        parentColumn = "payer_id",
        entityColumn = "participant_id"
    )
    val payer: ParticipantEntity
)
