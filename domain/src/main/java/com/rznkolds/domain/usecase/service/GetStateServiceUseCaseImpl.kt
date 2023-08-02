package com.rznkolds.domain.usecase.service

import com.rznkolds.data.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStateServiceUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : GetStateServiceUseCase {

    override operator fun invoke(): Flow<Boolean?> = repo.getStateService()
}