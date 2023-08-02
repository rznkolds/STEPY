package com.rznkolds.domain.usecase.user.ads

import com.rznkolds.data.repository.user.UserRepository
import javax.inject.Inject

class AddCountAdUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : AddCountAdUseCase {

    override suspend operator fun invoke() = repo.addCountAd()
}