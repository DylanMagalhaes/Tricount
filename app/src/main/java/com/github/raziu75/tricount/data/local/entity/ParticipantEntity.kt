package com.github.raziu75.tricount.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "participant")
data class ParticipantEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("participant_id")
    val participantId: Long = 0,
    val name: String
)
