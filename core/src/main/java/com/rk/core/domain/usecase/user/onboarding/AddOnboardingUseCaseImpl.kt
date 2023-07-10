package com.rk.core.domain.usecase.user.onboarding

import com.rk.core.domain.repository.UserRepository
import javax.inject.Inject

class AddOnboardingUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : AddOnboardingUseCase {

    override suspend operator fun invoke(complete: Boolean) = repo.addOnboarding(complete)
}