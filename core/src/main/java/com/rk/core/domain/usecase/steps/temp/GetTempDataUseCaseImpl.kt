package com.rk.core.domain.usecase.steps.temp

import com.rk.core.data.dto.Temp
import com.rk.core.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTempDataUseCaseImpl @Inject constructor(
    private val repo: UserRepository
): GetTempDataUseCase {

    override operator fun invoke(): Flow<Temp> = repo.getTempData()
}