package com.rznkolds.domain.model

import com.rznkolds.data.dto.Step

data class StepUI(
    val id: Int = 0,
    val name: String,
    val day: Int,
    val month: Int,
    val year: Int,
    val step: Int,
    val calorie: Int,
    val kilocalorie: Double,
    val meter: Int,
    val kilometer: Double,
    val target: Int,
    val state: Boolean
)
