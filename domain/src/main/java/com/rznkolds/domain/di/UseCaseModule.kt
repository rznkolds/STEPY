package com.rznkolds.domain.di

import com.rznkolds.domain.usecase.service.AddStateServiceUseCase
import com.rznkolds.domain.usecase.service.AddStateServiceUseCaseImpl
import com.rznkolds.domain.usecase.service.GetStateServiceUseCase
import com.rznkolds.domain.usecase.service.GetStateServiceUseCaseImpl
import com.rznkolds.domain.usecase.steps.month.GetMonthStepsUseCase
import com.rznkolds.domain.usecase.steps.month.GetMonthStepsUseCaseImpl
import com.rznkolds.domain.usecase.steps.step.AddStepUseCase
import com.rznkolds.domain.usecase.steps.step.AddStepUseCaseImpl
import com.rznkolds.domain.usecase.steps.step.GetStepUseCase
import com.rznkolds.domain.usecase.steps.step.GetStepUseCaseImpl
import com.rznkolds.domain.usecase.steps.temp.AddTempDataUseCase
import com.rznkolds.domain.usecase.steps.temp.AddTempDataUseCaseImpl
import com.rznkolds.domain.usecase.steps.temp.GetTempDataUseCase
import com.rznkolds.domain.usecase.steps.temp.GetTempDataUseCaseImpl
import com.rznkolds.domain.usecase.steps.week.GetWeekStepsUseCase
import com.rznkolds.domain.usecase.steps.week.GetWeekStepsUseCaseImpl
import com.rznkolds.domain.usecase.user.ads.AddCountAdUseCase
import com.rznkolds.domain.usecase.user.ads.AddCountAdUseCaseImpl
import com.rznkolds.domain.usecase.user.ads.GetCountAdUseCase
import com.rznkolds.domain.usecase.user.ads.GetCountAdUseCaseImpl
import com.rznkolds.domain.usecase.user.body.AddBodyDataUseCase
import com.rznkolds.domain.usecase.user.body.AddBodyDataUseCaseImpl
import com.rznkolds.domain.usecase.user.body.GetBodyDataUseCase
import com.rznkolds.domain.usecase.user.body.GetBodyDataUseCaseImpl
import com.rznkolds.domain.usecase.user.language.AddLanguageUseCase
import com.rznkolds.domain.usecase.user.language.AddLanguageUseCaseImpl
import com.rznkolds.domain.usecase.user.language.GetLanguageUseCase
import com.rznkolds.domain.usecase.user.language.GetLanguageUseCaseImpl
import com.rznkolds.domain.usecase.user.onboarding.AddOnboardingUseCase
import com.rznkolds.domain.usecase.user.onboarding.AddOnboardingUseCaseImpl
import com.rznkolds.domain.usecase.user.onboarding.GetOnboardingUseCase
import com.rznkolds.domain.usecase.user.onboarding.GetOnboardingUseCaseImpl
import com.rznkolds.domain.usecase.user.target.AddTargetUseCase
import com.rznkolds.domain.usecase.user.target.AddTargetUseCaseImpl
import com.rznkolds.domain.usecase.user.target.GetTargetUseCase
import com.rznkolds.domain.usecase.user.target.GetTargetUseCaseImpl
import com.rznkolds.domain.usecase.user.theme.AddThemeUseCase
import com.rznkolds.domain.usecase.user.theme.AddThemeUseCaseImpl
import com.rznkolds.domain.usecase.user.theme.GetThemeUseCase
import com.rznkolds.domain.usecase.user.theme.GetThemeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    // Get and add step usecase for room database

    @Binds
    @Singleton
    abstract fun bindAddStepUseCase(usecase: AddStepUseCaseImpl) : AddStepUseCase

    @Binds
    @Singleton
    abstract fun bindGetStepUseCase(usecase: GetStepUseCaseImpl): GetStepUseCase

    // Get and add temp data usecase for datastore database

    @Binds
    @Singleton
    abstract fun bindAddTempDataUseCase(usecase: AddTempDataUseCaseImpl): AddTempDataUseCase

    @Binds
    @Singleton
    abstract fun bindGetTempDataUseCase(usecase: GetTempDataUseCaseImpl): GetTempDataUseCase


    // Get week steps usecase for datastore database

    @Binds
    @Singleton
    abstract fun bindGetWeekStepsUseCase(usecase: GetWeekStepsUseCaseImpl): GetWeekStepsUseCase

    // Get month steps usecase for datastore database

    @Binds
    @Singleton
    abstract fun bindGetMonthStepsUseCase(usecase: GetMonthStepsUseCaseImpl): GetMonthStepsUseCase

    // Get and add onboarding complete usecase for datastore database

    @Binds
    @Singleton
    abstract fun bindAddOnboardingUseCase(usecase: AddOnboardingUseCaseImpl): AddOnboardingUseCase

    @Binds
    @Singleton
    abstract fun bindGetOnboardingUseCase(usecase: GetOnboardingUseCaseImpl): GetOnboardingUseCase

    // Get and add body data usecase for datastore database

    @Binds
    @Singleton
    abstract fun bindAddBodyDataUseCase(usecase: AddBodyDataUseCaseImpl): AddBodyDataUseCase

    @Binds
    @Singleton
    abstract fun bindGetBodyDataUseCase(usecase: GetBodyDataUseCaseImpl): GetBodyDataUseCase


    // Get and add target usecase for datastore database

    @Binds
    @Singleton
    abstract fun bindAddTargetUseCase(usecase: AddTargetUseCaseImpl): AddTargetUseCase

    @Binds
    @Singleton
    abstract fun bindGetTargetUseCase(usecase: GetTargetUseCaseImpl): GetTargetUseCase

    // Get and add state service usecase for datastore database

    @Binds
    @Singleton
    abstract fun bindAddStateServiceUseCase(usecase: AddStateServiceUseCaseImpl): AddStateServiceUseCase

    @Binds
    @Singleton
    abstract fun bindGetStateServiceUseCase(usecase: GetStateServiceUseCaseImpl): GetStateServiceUseCase

    // Get and add theme data usecase for datastore database

    @Binds
    @Singleton
    abstract fun bindAddThemeUseCase(usecase: AddThemeUseCaseImpl): AddThemeUseCase

    @Binds
    @Singleton
    abstract fun bindGetThemeUseCase(usecase: GetThemeUseCaseImpl): GetThemeUseCase

    // Get and add language data usecase for datastore database

    @Binds
    @Singleton
    abstract fun bindAddLanguageUseCase(usecase: AddLanguageUseCaseImpl): AddLanguageUseCase

    @Binds
    @Singleton
    abstract fun bindGetLanguageUseCase(usecase: GetLanguageUseCaseImpl): GetLanguageUseCase

    // Get and add count ads usecase for datastore database

    @Binds
    @Singleton
    abstract fun bindAddCountAdUseCase(usecase: AddCountAdUseCaseImpl): AddCountAdUseCase

    @Binds
    @Singleton
    abstract fun bindGetCountAdUseCase(usecase: GetCountAdUseCaseImpl): GetCountAdUseCase
}