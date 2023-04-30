package com.github.raziu75.tricount.data.injection

import android.app.Application
import com.github.raziu75.tricount.data.local.TricountDatabase
import com.github.raziu75.tricount.data.local.dao.TricountDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideTricountDataBase(app: Application): TricountDatabase {
        return TricountDatabase.getInstance(app.baseContext)
    }

    @Singleton
    @Provides
    fun provideTricountDao(database: TricountDatabase): TricountDao {
        return database.dao
    }
}