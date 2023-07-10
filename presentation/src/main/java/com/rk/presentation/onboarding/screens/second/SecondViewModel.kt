package com.rk.presentation.onboarding.screens.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rk.core.domain.usecase.user.body.AddBodyDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    private val addBodyDataUseCase: AddBodyDataUseCase
) : ViewModel() {

    fun addBmi(weight: Int, height: Int) {
        viewModelScope.launch { addBodyDataUseCase(weight, height) }
    }
}