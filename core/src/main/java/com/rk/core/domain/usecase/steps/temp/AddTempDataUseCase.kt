package com.rk.core.domain.usecase.steps.temp

interface AddTempDataUseCase {

    suspend operator fun invoke(temp: Int, calorie:Double, kilometer:Double)
}