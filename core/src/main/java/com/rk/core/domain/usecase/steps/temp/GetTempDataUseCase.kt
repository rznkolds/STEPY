package com.rk.core.domain.usecase.steps.temp

import com.rk.core.data.dto.Temp
import kotlinx.coroutines.flow.Flow

interface GetTempDataUseCase {

    operator fun invoke(): Flow<Temp>
}