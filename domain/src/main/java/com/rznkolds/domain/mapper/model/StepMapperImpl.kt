package com.rznkolds.domain.mapper.model

import com.rznkolds.data.dto.Step
import com.rznkolds.domain.mapper.model.StepMapper
import com.rznkolds.domain.model.StepUI
import javax.inject.Inject

class StepMapperImpl @Inject constructor() : StepMapper<Step, StepUI> {
    override fun map(input: Step?): StepUI {
        return StepUI(
            id = input?.id ?: 0,
            name = input?.name ?: "",
            day = input?.day ?: 0,
            month = input?.month ?: 0,
            year = input?.year ?: 0,
            step = input?.step ?: 0,
            calorie = input?.calorie ?: 0,
            kilocalorie = input?.kilocalorie ?: 0.0,
            meter = input?.meter ?: 0,
            kilometer = input?.kilometer ?: 0.0,
            target = input?.target ?: 0,
            state = input?.state ?: false
        )
    }
}