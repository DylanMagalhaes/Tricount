package com.github.raziu75.tricount.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.raziu75.tricount.data.local.dao.TricountDao
import com.github.raziu75.tricount.data.local.entity.ParticipantEntity
import com.github.raziu75.tricount.data.local.entity.TransactionEntity
import com.github.raziu75.tricount.data.local.entity.TripEntity

@Database(
    entities = [
        TransactionEntity::class,
        ParticipantEntity::class,
        TripEntity::class,
    ],
    version = 1
)
abstract class TricountDatabase : RoomDatabase() {

    abstract val dao: TricountDao

    companion object {
        private const val DATABASE_NAME = "tricount_db"
        @Volatile private var INSTANCE: TricountDatabase? = null

        fun getInstance(context: Context): TricountDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = TricountDatabase::class.java,
                    name = DATABASE_NAME,
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}