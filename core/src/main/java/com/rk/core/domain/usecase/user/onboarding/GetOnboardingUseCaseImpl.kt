package com.rk.core.domain.usecase.user.onboarding

import com.rk.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOnboardingUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : GetOnboardingUseCase {

    override operator fun invoke(): Flow<Boolean?> = repo.getOnboarding()
}