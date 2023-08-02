package com.rznkolds.domain.usecase.user.onboarding

import com.rznkolds.data.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOnboardingUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : GetOnboardingUseCase {

    override operator fun invoke(): Flow<Boolean?> = repo.getOnboarding()
}