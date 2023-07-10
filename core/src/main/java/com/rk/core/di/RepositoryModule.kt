package com.rk.core.di

import com.rk.core.data.repository.StepRepositoryImpl
import com.rk.core.data.repository.UserRepositoryImpl
import com.rk.core.domain.repository.StepRepository
import com.rk.core.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStepRepository(repo:StepRepositoryImpl): StepRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(repo: UserRepositoryImpl): UserRepository
}