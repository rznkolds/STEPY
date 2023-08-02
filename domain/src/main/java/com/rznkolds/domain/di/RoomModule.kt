package com.rznkolds.domain.di

import android.content.Context
import androidx.room.Room
import com.rznkolds.data.db.StepDatabase
import com.rznkolds.data.source.room.StepDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "stepy_database"
@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): StepDatabase {

        return Room.databaseBuilder(
            context,
            StepDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideStepDao(database: StepDatabase): StepDao = database.dao()
}