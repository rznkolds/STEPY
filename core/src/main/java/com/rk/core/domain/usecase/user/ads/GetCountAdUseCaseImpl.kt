package com.rk.core.domain.usecase.user.ads

import com.rk.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountAdUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : GetCountAdUseCase {

    override operator fun invoke(): Flow<Int?> = repo.getCountAd()
}