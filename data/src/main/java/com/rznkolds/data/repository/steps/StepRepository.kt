package com.rznkolds.data.repository.steps

import com.rznkolds.data.dto.Step

interface StepRepository {

    // Get and add step data methods for repository

    suspend fun addStep(step: Step)

    suspend fun getStep(day: Int, week: Int, year: Int): Step

    // Get week steps metod for repository

    suspend fun getWeekSteps(): List<Step>

    // Get month steps metod for repository

    suspend fun getMonthSteps(): List<Step>
}