package com.rk.core.domain.usecase.steps.step

import com.rk.core.data.dto.Step

interface AddStepUseCase {

    suspend operator fun invoke(step: Step)
}