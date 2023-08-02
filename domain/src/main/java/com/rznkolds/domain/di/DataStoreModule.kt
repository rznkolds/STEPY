package com.rznkolds.domain.di

import android.content.Context
import com.rznkolds.data.source.datastore.UserDataSource
import com.rznkolds.data.source.datastore.UserDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): UserDataSource = UserDataSourceImpl(context)
}