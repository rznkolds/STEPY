package com.rk.presentation.onboarding.screens.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rk.core.domain.usecase.user.target.AddTargetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThirdViewModel @Inject constructor(
    private val addTargetUseCase: AddTargetUseCase
) : ViewModel() {

    fun addTarget(target: Int) {
        viewModelScope.launch { addTargetUseCase(target) }
    }
}