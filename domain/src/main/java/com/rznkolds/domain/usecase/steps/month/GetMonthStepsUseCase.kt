package com.rznkolds.domain.usecase.steps.month

import com.rznkolds.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import com.rznkolds.data.dto.Step
import com.rznkolds.domain.model.StepUI

interface GetMonthStepsUseCase {

    operator fun invoke(): Flow<Resource<List<StepUI>>>
}