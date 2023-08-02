package com.rznkolds.domain.usecase.steps.step

import com.rznkolds.data.dto.Step
import com.rznkolds.data.repository.steps.StepRepository
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.mapper.model.StepMapper
import com.rznkolds.domain.model.StepUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class GetStepUseCaseImpl @Inject constructor(
    private val repo: StepRepository,
    private val mapper: StepMapper<Step,StepUI>,
) : GetStepUseCase {

    override operator fun invoke(day: Int, week: Int, year: Int): Flow<Resource<StepUI>> = flow {

        try {
            emit(Resource.Loading)

            emit(Resource.Success(mapper.map(repo.getStep(day, week, year))))

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach server."))
        }
    }.flowOn(Dispatchers.IO)
}