package com.rk.core.di

import android.content.Context
import com.rk.core.data.source.local.UserDataSourceImpl
import com.rk.core.domain.source.local.UserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SourceModule {

    @Provides
    @Singleton
    fun provideDataSource(@ApplicationContext context: Context): UserDataSource = UserDataSourceImpl(context)
}