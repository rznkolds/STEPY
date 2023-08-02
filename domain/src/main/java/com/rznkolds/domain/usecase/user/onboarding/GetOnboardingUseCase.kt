package com.rznkolds.domain.usecase.user.onboarding

import kotlinx.coroutines.flow.Flow

interface GetOnboardingUseCase {

    operator fun invoke(): Flow<Boolean?>
}