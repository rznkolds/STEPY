package com.rk.core.domain.usecase.service

import com.rk.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStateServiceUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : GetStateServiceUseCase {

    override operator fun invoke(): Flow<Boolean?> = repo.getStateService()
}