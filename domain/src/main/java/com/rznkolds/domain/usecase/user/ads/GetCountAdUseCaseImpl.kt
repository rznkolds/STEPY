package com.rznkolds.domain.usecase.user.ads

import com.rznkolds.data.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCountAdUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : GetCountAdUseCase {

    override operator fun invoke(): Flow<Int?> = repo.getCountAd()
}