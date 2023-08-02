package com.rk.presentation.ui.home

import com.rznkolds.domain.model.StepUI
import com.rznkolds.domain.model.TempUI

data class HomeUIState(
    val week: List<StepUI>? = null,
    val loading: String? = null,
    val error: String? = null,
    val target: Int? = null,
    val number: Int? = null,
    val temp: TempUI? = null,
)