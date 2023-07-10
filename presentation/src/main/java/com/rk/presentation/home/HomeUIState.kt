package com.rk.presentation.home

import com.rk.core.data.dto.Step
import com.rk.core.data.dto.Temp

data class HomeUIState(
    val week: List<Step>? = null,
    val loading: String? = null,
    val error: String? = null,
    val target: Int? = null,
    val number: Int? = null,
    val temp: Temp? = null,
)