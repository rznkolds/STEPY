package com.rk.presentation.onboarding.screens.fourth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rk.core.domain.usecase.user.onboarding.AddOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FourthViewModel @Inject constructor(
    private val addOnBoardingUseCase: AddOnboardingUseCase,
) : ViewModel() {

    fun addOnboarding (complete: Boolean) {
        viewModelScope.launch { addOnBoardingUseCase(complete) }
    }
}