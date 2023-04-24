package com.github.raziu75.tricount.data.entity.relation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.github.raziu75.tricount.data.entity.ParticipantEntity
import com.github.raziu75.tricount.data.entity.TripEntity

@Entity(
    tableName = "trip_participant",
    primaryKeys = ["trip_id", "participant_id"],
    foreignKeys = [
        ForeignKey(
            entity = TripEntity::class,
            parentColumns = ["trip_id"],
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
data class TripTransactionsCrossRef(
    @ColumnInfo("trip_id") val tripId: Long,
    @ColumnInfo("participant_id") val participantId: String,
)