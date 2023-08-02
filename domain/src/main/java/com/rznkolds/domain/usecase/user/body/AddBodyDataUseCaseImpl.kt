package com.rznkolds.domain.usecase.user.body

import com.rznkolds.data.repository.user.UserRepository
import javax.inject.Inject

class AddBodyDataUseCaseImpl @Inject constructor(
    private val repo: UserRepository
) : AddBodyDataUseCase {

    override suspend operator fun invoke(weight: Int, height: Int) = repo.addBodyData(weight, height)
}