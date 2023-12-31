package com.rznkolds.data.repository.steps

import com.rznkolds.data.dto.Step
import com.rznkolds.data.source.room.StepDao
import javax.inject.Inject

class StepRepositoryImpl @Inject constructor(
    private val dao: StepDao
): StepRepository {

    // Get and add step data methods for repository

    override suspend fun addStep(step: Step) = dao.addStep(step)

    override suspend fun getStep(day: Int, week: Int, year: Int): Step = dao.getStep(day, week, year)

    // Get week steps metod for repository

    override suspend fun getWeekSteps(): List<Step> = dao.getWeekSteps()

    // Get month steps metod for repository

    override suspend fun getMonthSteps(): List<Step> = dao.getMonthSteps()
}