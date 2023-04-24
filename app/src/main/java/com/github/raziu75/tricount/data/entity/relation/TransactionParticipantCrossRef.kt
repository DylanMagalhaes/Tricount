package com.github.raziu75.tricount.data.entity.relation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.github.raziu75.tricount.data.entity.ParticipantEntity
import com.github.raziu75.tricount.data.entity.TransactionEntity

@Entity(
    tableName = "transaction_participant",
    primaryKeys = ["transaction_id", "participant_id"],
    foreignKeys = [
        ForeignKey(
            entity = TransactionEntity::class,
            parentColumns = ["transaction_id"],
            childColumns = ["transaction_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ParticipantEntity::class,
            parentColumns = ["participant_id"],
            childColumns = ["participant_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TransactionParticipantCrossRef(
    @ColumnInfo("transaction_id") val transactionId: Long,
    @ColumnInfo("participant_id") val participantId: String,
    @ColumnInfo("is_payer") val isPayer: Boolean,
)