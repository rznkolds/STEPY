package com.rk.core.domain.usecase.steps.month

import com.rk.core.common.Resource
import com.rk.core.data.dto.Step
import kotlinx.coroutines.flow.Flow

interface GetMonthStepsUseCase {

    operator fun invoke(): Flow<Resource<List<Step>>>
}