package com.rk.core.domain.usecase.user.body

import com.rk.core.domain.repository.UserRepository
import javax.inject.Inject

class AddBodyDataUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : AddBodyDataUseCase {

    override suspend operator fun invoke(weight: Int, height: Int) = repo.addBodyData(weight, height)
}