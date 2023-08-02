package com.rznkolds.domain.di

import com.rznkolds.data.dto.Step
import com.rznkolds.domain.mapper.list.StepListMapper
import com.rznkolds.domain.mapper.list.StepListMapperImpl
import com.rznkolds.domain.mapper.model.StepMapper
import com.rznkolds.domain.mapper.model.StepMapperImpl
import com.rznkolds.domain.mapper.model.StepUIMapperImpl
import com.rznkolds.domain.model.StepUI
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindStepMapper(stepMapper: StepMapperImpl): StepMapper<Step, StepUI>

    @Binds
    abstract fun bindStepListMapper(stepListMapper: StepListMapperImpl): StepListMapper<Step, StepUI>

    @Binds
    abstract fun bindStepUIMapper(stepMapper: StepUIMapperImpl): StepMapper<StepUI, Step>
}