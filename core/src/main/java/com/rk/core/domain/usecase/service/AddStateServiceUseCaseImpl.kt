package com.rk.core.domain.usecase.service

import com.rk.core.domain.repository.UserRepository
import javax.inject.Inject

class AddStateServiceUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : AddStateServiceUseCase {

    override suspend operator fun invoke(state: Boolean) = repo.addStateService (state)
}