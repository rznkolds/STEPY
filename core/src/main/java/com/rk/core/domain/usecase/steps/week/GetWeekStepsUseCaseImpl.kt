package com.rk.core.domain.usecase.steps.week

import com.rk.core.common.Resource
import com.rk.core.data.dto.Step
import com.rk.core.domain.repository.StepRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class GetWeekStepsUseCaseImpl @Inject constructor(
    private val repo: StepRepository
): GetWeekStepsUseCase {

    override operator fun invoke(): Flow<Resource<List<Step>>> = flow {

        try {
            emit(Resource.Loading)

            emit(Resource.Success(repo.getWeekSteps()))

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach server."))
        }
    }.flowOn(Dispatchers.IO)
}