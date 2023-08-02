package com.rznkolds.domain.di

import com.rznkolds.data.repository.steps.StepRepository
import com.rznkolds.data.repository.steps.StepRepositoryImpl
import com.rznkolds.data.repository.user.UserRepository
import com.rznkolds.data.repository.user.UserRepositoryImpl
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
    abstract fun bindStepRepository(repo: StepRepositoryImpl): StepRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(repo: UserRepositoryImpl): UserRepository
}