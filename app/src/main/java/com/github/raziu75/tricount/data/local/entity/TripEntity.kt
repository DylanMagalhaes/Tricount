package com.github.raziu75.tricount.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trip")
data class TripEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "trip_id") val tripId: Long,
    val title: String,
    val description: String,
)
