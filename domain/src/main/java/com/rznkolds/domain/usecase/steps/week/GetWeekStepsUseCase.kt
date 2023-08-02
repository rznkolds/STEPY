package com.rznkolds.domain.usecase.steps.week

import com.rznkolds.data.dto.Step
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.StepUI
import kotlinx.coroutines.flow.Flow

interface GetWeekStepsUseCase {

    operator fun invoke(): Flow<Resource<List<StepUI>>>
}