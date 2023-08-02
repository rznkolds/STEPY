package com.rznkolds.domain.mapper.list

import com.rznkolds.data.dto.Step
import com.rznkolds.domain.model.StepUI
import javax.inject.Inject

class StepListMapperImpl @Inject constructor() : StepListMapper<Step, StepUI> {
    override fun map(input: List<Step>?): List<StepUI> {
        return input?.map {
            StepUI(
                id = it.id,
                name = it.name,
                day = it.day,
                month = it.month,
                year = it.year,
                step = it.step,
                calorie = it.calorie,
                kilocalorie = it.kilocalorie,
                meter = it.meter,
                kilometer = it.kilometer,
                target = it.target,
                state = it.state
            )
        } ?: emptyList()
    }
}