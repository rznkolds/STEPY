package com.rk.core.domain.usecase.user.body

interface AddBodyDataUseCase {

    suspend operator fun invoke(weight: Int, height: Int)
}