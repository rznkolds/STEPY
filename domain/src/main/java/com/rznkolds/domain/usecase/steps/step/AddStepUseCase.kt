package com.rznkolds.domain.usecase.steps.step

import com.rznkolds.domain.model.StepUI

interface AddStepUseCase {

    suspend operator fun invoke(step: StepUI)
}