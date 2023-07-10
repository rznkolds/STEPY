package com.rk.core.domain.usecase.user.ads

import com.rk.core.domain.repository.UserRepository
import javax.inject.Inject

class AddCountAdUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : AddCountAdUseCase {

    override suspend operator fun invoke() = repo.addCountAd()
}