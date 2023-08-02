package com.rznkolds.domain.usecase.steps.week

import com.rznkolds.data.dto.Step
import com.rznkolds.data.repository.steps.StepRepository
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.mapper.list.StepListMapper
import com.rznkolds.domain.model.StepUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class GetWeekStepsUseCaseImpl @Inject constructor(
    private val repo: StepRepository,
    private val mapper: StepListMapper<Step,StepUI>
) : GetWeekStepsUseCase {

    override operator fun invoke(): Flow<Resource<List<StepUI>>> = flow {

        try {
            emit(Resource.Loading)

            emit(Resource.Success(mapper.map(repo.getWeekSteps())))

        } catch (e: IOException) {

            emit(Resource.Error("Couldn't reach server."))
        }
    }.flowOn(Dispatchers.IO)
}