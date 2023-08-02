package com.rznkolds.domain.usecase.user.onboarding

import com.rznkolds.data.repository.user.UserRepository
import javax.inject.Inject

class AddOnboardingUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : AddOnboardingUseCase {

    override suspend operator fun invoke(complete: Boolean) = repo.addOnboarding(complete)
}