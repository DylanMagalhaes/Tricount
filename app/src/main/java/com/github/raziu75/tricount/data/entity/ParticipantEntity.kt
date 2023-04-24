package com.github.raziu75.tricount.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "participant")
data class ParticipantEntity(
    @PrimaryKey(autoGenerate = true) val participantId: String,
    val name: String
)
