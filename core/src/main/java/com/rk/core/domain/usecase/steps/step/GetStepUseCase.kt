package com.rk.core.domain.usecase.steps.step

import com.rk.core.common.Resource
import com.rk.core.data.dto.Step
import kotlinx.coroutines.flow.Flow

interface GetStepUseCase {

    operator fun invoke(day: Int, week: Int, year: Int): Flow<Resource<Step>>
}