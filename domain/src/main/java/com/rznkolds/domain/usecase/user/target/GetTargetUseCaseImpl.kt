package com.rznkolds.domain.usecase.user.target

import com.rznkolds.data.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTargetUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : GetTargetUseCase {

    override operator fun invoke(): Flow<Int?> = repo.getTarget()
}