package com.rk.core.domain.usecase.user.target

import com.rk.core.domain.repository.UserRepository
import javax.inject.Inject

class AddTargetUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : AddTargetUseCase {

    override suspend operator fun invoke(target: Int) = repo.addTarget(target)
}