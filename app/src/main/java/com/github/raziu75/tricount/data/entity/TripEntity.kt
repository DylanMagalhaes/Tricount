package com.github.raziu75.tricount.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "trip")
data class TripEntity(
    @ColumnInfo(name = "trip_id") val tripId: String,
    val title: String,
    val description: String,
)
