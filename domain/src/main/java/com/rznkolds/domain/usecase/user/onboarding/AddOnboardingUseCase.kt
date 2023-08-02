package com.rznkolds.domain.usecase.user.onboarding

interface AddOnboardingUseCase {

    suspend operator fun invoke(complete:Boolean)
}