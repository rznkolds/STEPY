package com.rznkolds.domain.usecase.steps.step

import com.rznkolds.data.dto.Step
import com.rznkolds.data.repository.steps.StepRepository
import com.rznkolds.domain.mapper.model.StepMapper
import com.rznkolds.domain.model.StepUI
import javax.inject.Inject

class AddStepUseCaseImpl @Inject constructor(
    private val repo: StepRepository,
    private val mapper: StepMapper<StepUI,Step>
): AddStepUseCase {

    override suspend operator fun invoke(step: StepUI) = repo.addStep(mapper.map(step))
}