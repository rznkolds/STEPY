package com.rznkolds.domain.usecase.service

import com.rznkolds.data.repository.user.UserRepository
import javax.inject.Inject

class AddStateServiceUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : AddStateServiceUseCase {

    override suspend operator fun invoke(state: Boolean) = repo.addStateService (state)
}