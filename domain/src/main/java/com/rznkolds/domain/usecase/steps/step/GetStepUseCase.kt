package com.rznkolds.domain.usecase.steps.step

import com.rznkolds.data.dto.Step
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.StepUI
import kotlinx.coroutines.flow.Flow

interface GetStepUseCase {

    operator fun invoke(day: Int, week: Int, year: Int): Flow<Resource<StepUI>>
}