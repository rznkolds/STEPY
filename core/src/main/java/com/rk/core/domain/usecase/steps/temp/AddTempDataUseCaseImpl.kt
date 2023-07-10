package com.rk.core.domain.usecase.steps.temp

import com.rk.core.domain.repository.UserRepository
import javax.inject.Inject

class AddTempDataUseCaseImpl @Inject constructor(
    private val repo: UserRepository
): AddTempDataUseCase {

    override suspend operator fun invoke(temp: Int, calorie:Double, kilometer:Double) = repo.addTempData(temp, calorie, kilometer)
}