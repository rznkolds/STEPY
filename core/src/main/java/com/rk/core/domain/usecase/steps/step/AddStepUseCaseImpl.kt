package com.rk.core.domain.usecase.steps.step

import com.rk.core.data.dto.Step
import com.rk.core.domain.repository.StepRepository
import javax.inject.Inject

class AddStepUseCaseImpl @Inject constructor(
    private val repo: StepRepository
): AddStepUseCase {

    override suspend operator fun invoke(step: Step) = repo.addStep(step)
}