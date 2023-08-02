package com.rznkolds.domain.usecase.user.target

import com.rznkolds.data.repository.user.UserRepository
import javax.inject.Inject

class AddTargetUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : AddTargetUseCase {

    override suspend operator fun invoke(target: Int) = repo.addTarget(target)
}