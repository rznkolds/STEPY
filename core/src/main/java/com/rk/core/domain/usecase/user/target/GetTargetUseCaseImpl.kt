package com.rk.core.domain.usecase.user.target

import com.rk.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTargetUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : GetTargetUseCase {

    override operator fun invoke(): Flow<Int?> = repo.getTarget()
}