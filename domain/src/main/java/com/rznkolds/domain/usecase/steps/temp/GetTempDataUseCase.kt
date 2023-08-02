package com.rznkolds.domain.usecase.steps.temp

import com.rznkolds.data.dto.Temp
import com.rznkolds.domain.common.Resource
import com.rznkolds.domain.model.StepUI
import com.rznkolds.domain.model.TempUI
import kotlinx.coroutines.flow.Flow

interface GetTempDataUseCase {

    operator fun invoke(): Flow<TempUI>
}