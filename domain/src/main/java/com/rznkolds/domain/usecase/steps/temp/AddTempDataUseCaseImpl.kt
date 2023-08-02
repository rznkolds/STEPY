package com.rznkolds.domain.usecase.steps.temp

import com.rznkolds.data.repository.user.UserRepository
import javax.inject.Inject

class AddTempDataUseCaseImpl @Inject constructor(
    private val repo: UserRepository
): AddTempDataUseCase {

    override suspend operator fun invoke(temp: Int, calorie:Double, kilometer:Double) = repo.addTempData(temp, calorie, kilometer)
}