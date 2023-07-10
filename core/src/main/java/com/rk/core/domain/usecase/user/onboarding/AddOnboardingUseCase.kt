package com.rk.core.domain.usecase.user.onboarding

interface AddOnboardingUseCase {

    suspend operator fun invoke(complete:Boolean)
}